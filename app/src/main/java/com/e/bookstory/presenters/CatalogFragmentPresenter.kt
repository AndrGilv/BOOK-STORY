package com.e.bookstory.presenters

import android.content.Context
import com.e.bookstory.model.AppModel
import com.e.bookstory.model.AppModelInterface
import com.e.bookstory.observers.BooksObserver
import com.e.bookstory.entities.Book
import com.e.bookstory.fragments.views.CatalogFragmentView
import java.util.ArrayList

class CatalogFragmentPresenter(context: Context): Presenter(context), BooksObserver {

    private var catalogFragmentView: CatalogFragmentView? = null
    val books: ArrayList<Book>
        get() {return model.getBooks()}
    var loading: Boolean = false

    init {
        model.registerBookObservers(this)
    }

    fun attachCatalogFragmentView(catalogFragmentView: CatalogFragmentView){
        this.catalogFragmentView = catalogFragmentView;
    }

    fun detachCatalogFragmentView(){
        catalogFragmentView = null
    }

    fun getNextBooksPortion(){
        loading = true

        //books.addAll(model.loadNextBooksPortion())// эта чсть изменится когда заработает сервак
        //update()
        model.loadNextBooksPortion()

        loading = false
    }

    override fun update() {
        catalogFragmentView?.notifyRecyclerView()
    }

}