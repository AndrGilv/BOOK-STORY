package com.e.bookstory.presenters

import android.content.Context
import com.e.bookstory.model.AppModel
import com.e.bookstory.model.AppModelInterface
import com.e.bookstory.activities.views.MainActivityView

class MainActivityPresenter(context: Context): Presenter(context) {

    private var mainActivityView: MainActivityView? = null
    /*val books: ArrayList<Book>
        get() {return model.getBooks()}
    var loading: Boolean = false*/

    init {
        /*model.registerBookObservers(this)*/
    }

    fun attachView(mainActivityView: MainActivityView){
        this.mainActivityView = mainActivityView;
    }

    fun detachView(){
        mainActivityView = null
        model.closeDB()
    }

    /*fun getNextBooksPortion(){
        loading = true

        books.addAll(model.loadNextBooksPortion())// эта чсть изменится когда заработает сервак
        update()

        loading = false
    }*/

    /*override fun update() {
        123
    }*/
}