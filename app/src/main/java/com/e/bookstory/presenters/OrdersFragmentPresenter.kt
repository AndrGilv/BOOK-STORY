package com.e.bookstory.presenters

import android.content.Context
import com.e.bookstory.model.AppModel
import com.e.bookstory.model.AppModelInterface
import com.e.bookstory.entities.Order
import com.e.bookstory.fragments.views.OrdersFragmentView
import com.e.bookstory.observers.OrderObserver
import java.util.ArrayList

class OrdersFragmentPresenter(context: Context): OrderObserver {
    private val model: AppModelInterface =
        AppModel(context)
    private var ordersFragmentView: OrdersFragmentView? = null

    val orders: ArrayList<Order>
        get() {return model.getOrders()}

    var loading: Boolean = false

    init {
        model.registerOrderObservers(this)
    }

    fun attachView(ordersFragmentView: OrdersFragmentView){
        this.ordersFragmentView = ordersFragmentView;
    }

    fun detachView(){
        ordersFragmentView = null
    }

    fun getNextOrdersPortion(){
        loading = true

        //orders.addAll(model.loadNextOrdersPortion())
        //update()
        model.loadNextOrdersPortion()

        loading = false
    }

    override fun update() {
        ordersFragmentView?.notifyRecyclerView()
    }
}