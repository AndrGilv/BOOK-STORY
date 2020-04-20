package com.e.bookstory.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.activityAdapters.OrdersRVAdapter
import com.e.bookstory.R
import com.e.bookstory.activityAdapters.CartRVAdapter
import com.e.bookstory.entities.Book
import com.e.bookstory.entities.Order
import com.e.bookstory.fragments.views.OrdersFragmentView
import com.e.bookstory.presenters.CatalogFragmentPresenter
import com.e.bookstory.presenters.OrdersFragmentPresenter
import com.google.gson.Gson
import java.util.*

class OrdersFragment : Fragment(), OrdersFragmentView{

    private lateinit var rv: RecyclerView

    private lateinit var presenter: OrdersFragmentPresenter

    private lateinit var adapter: OrdersRVAdapter
    private val ADAPTER_KEY = "ADAPTER"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = OrdersFragmentPresenter(view.context)
        presenter.attachView(this)
        rv = view.findViewById(R.id.ordersRView)
        val llm = LinearLayoutManager(view.context)
        rv.layoutManager = llm

        /*adapter = if(savedInstanceState != null){
            Gson().fromJson(savedInstanceState.getString(ADAPTER_KEY), OrdersRVAdapter::class.java)
        }
        else{
            OrdersRVAdapter(
                presenter.orders,
                view.context
            )
        }*/

        adapter = OrdersRVAdapter(
                presenter.orders,
                view.context
            )

        rv.adapter = adapter

        presenter.getNextOrdersPortion()
        rv.addOnScrollListener(object: RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0) //check for scroll down
                {

                    val totalItemCount = llm.itemCount
                    val lastVisibleItem = llm.findLastVisibleItemPosition()

                    if(!presenter.loading){
                        if((lastVisibleItem)  >= totalItemCount - 2){
                            presenter.getNextOrdersPortion()
                        }
                    }
                }

            }
        })


    }

    override fun notifyRecyclerView() {
        if( rv.adapter == null){
            Log.e("mylog OrderFragment", "rvAdapter == null")
        }
        else{
            rv.adapter!!.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putString(ADAPTER_KEY, Gson().toJson(adapter))
    }

}
