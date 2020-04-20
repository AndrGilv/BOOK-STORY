package com.e.bookstory.presenters

import android.content.Context
import com.e.bookstory.model.AppModel
import com.e.bookstory.model.AppModelInterface
import com.e.bookstory.entities.Purchase
import com.e.bookstory.fragments.views.CartFragmentView
import com.e.bookstory.observers.PurchaseObserver
import java.util.ArrayList

class CartFragmentPresenter(context: Context): Presenter(context), PurchaseObserver {

    private var cartFragmentView: CartFragmentView? = null

    val purchases: ArrayList<Purchase>
        get() {return model.getPurchases()}

    var loading: Boolean = false

    init {
        model.registerPurchaseObservers(this)
    }

    fun attachView(cartFragmentView: CartFragmentView){
        this.cartFragmentView = cartFragmentView;
    }

    fun detachView(){
        cartFragmentView = null
    }

    fun getNextPurchasePortion(){
        loading = true

        //purchases.addAll(model.loadNextPurchasePortion())
        //update()
        model.loadNextPurchasePortion()

        loading = false
    }

    override fun update() {
        cartFragmentView?.notifyRecyclerView()
    }
}