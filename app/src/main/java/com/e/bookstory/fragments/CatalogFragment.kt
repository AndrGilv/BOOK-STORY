package com.e.bookstory.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.presenters.MainActivityPresenter
import com.e.bookstory.activityAdapters.CatalogRVAdapter
import com.e.bookstory.R
import com.e.bookstory.activityAdapters.CartRVAdapter
import com.e.bookstory.entities.Book
import com.e.bookstory.fragments.views.CatalogFragmentView
import com.e.bookstory.presenters.CatalogFragmentPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import java.util.ArrayList


class CatalogFragment : Fragment(), CatalogFragmentView {

    private lateinit var rv: RecyclerView

    private lateinit var presenter: CatalogFragmentPresenter

    private lateinit var adapter: CatalogRVAdapter
    private val ADAPTER_KEY = "ADAPTER"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = CatalogFragmentPresenter(view.context)
        presenter.attachCatalogFragmentView(this)
        rv = view.findViewById(R.id.catalogRView)

        /*adapter = if(savedInstanceState != null){
            Gson().fromJson(savedInstanceState.getString(ADAPTER_KEY), CatalogRVAdapter::class.java)
        }
        else{
            CatalogRVAdapter( presenter.books, view.context)
        }*/
        adapter = CatalogRVAdapter( presenter.books, view.context)


        val llm = LinearLayoutManager(view.context)
        rv.layoutManager = llm
        rv.adapter = adapter
        presenter.getNextBooksPortion()
        rv.addOnScrollListener(object: RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0) //check for scroll down
                {

                    val totalItemCount = llm.itemCount
                    val lastVisibleItem = llm.findLastVisibleItemPosition()

                    if(!presenter.loading){
                        if((lastVisibleItem)  >= totalItemCount - 2){
                            presenter.getNextBooksPortion()
                        }
                    }
                }

            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachCatalogFragmentView()
    }

    override fun notifyRecyclerView() {
        if( rv.adapter == null){
            Log.e("mylog CatalogFragment", "rvAdapter == null")
        }
        else{
            rv.adapter!!.notifyDataSetChanged()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putString(ADAPTER_KEY, Gson().toJson(adapter))
    }

}
