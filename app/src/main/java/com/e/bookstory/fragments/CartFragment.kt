package com.e.bookstory.fragments

import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.activityAdapters.CartRVAdapter
import com.e.bookstory.R
import com.e.bookstory.entities.Book
import com.e.bookstory.entities.Purchase
import com.e.bookstory.fragments.views.CartFragmentView
import com.e.bookstory.presenters.CartFragmentPresenter
import com.e.bookstory.presenters.CatalogFragmentPresenter
import com.google.gson.Gson
import java.util.*


class CartFragment : Fragment(), CartFragmentView{

    private lateinit var rv: RecyclerView
    private val SAVED_RECYCLER_VIEW_STATUS_ID = "recyclerView"

    private lateinit var presenter: CartFragmentPresenter

    private lateinit var adapter: CartRVAdapter
    private val ADAPTER_KEY = "ADAPTER"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = CartFragmentPresenter(view.context)
        presenter.attachView(this)
        rv = view.findViewById(R.id.cartRView)
        val llm = LinearLayoutManager(view.context)
        rv.layoutManager = llm
        if(savedInstanceState != null){
            val listState: Parcelable = savedInstanceState.getParcelable(SAVED_RECYCLER_VIEW_STATUS_ID)!!
            rv.layoutManager!!.onRestoreInstanceState(listState);
        }
        adapter = CartRVAdapter(presenter.purchases,view.context)
        rv.adapter = adapter

        rv.addOnScrollListener(object: RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0) //check for scroll down
                {
                    val totalItemCount = llm.itemCount
                    val lastVisibleItem = llm.findLastVisibleItemPosition()
                    if(!presenter.loading){
                        if((lastVisibleItem)  >= totalItemCount - 2){
                            presenter.getNextPurchasePortion()
                        }
                    }
                }

            }
        })

        /*adapter = if(savedInstanceState != null){
            Gson().fromJson(savedInstanceState.getString(ADAPTER_KEY), CartRVAdapter::class.java)
        }
        else{
            CartRVAdapter(
                presenter.purchases,
                view.context
            )
        }*/

        presenter.getNextPurchasePortion()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun notifyRecyclerView() {
        rv.adapter!!.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putString(ADAPTER_KEY, Gson().toJson(adapter))
        val liststate: Parcelable = rv.layoutManager!!.onSaveInstanceState()!!
        outState.putParcelable(SAVED_RECYCLER_VIEW_STATUS_ID, liststate)

    }
}
