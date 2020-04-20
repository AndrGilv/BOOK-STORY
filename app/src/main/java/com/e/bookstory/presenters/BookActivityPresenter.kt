package com.e.bookstory.presenters

import android.content.Context
import com.e.bookstory.View
import com.e.bookstory.model.AppModel
import com.e.bookstory.model.AppModelInterface
import com.e.bookstory.activities.views.BookActivityView

class BookActivityPresenter(context: Context): Presenter(context) {

    private var bookActivityView: BookActivityView? = null

    fun attachView(bookActivityView: BookActivityView){
        this.bookActivityView = bookActivityView
    }

    fun detachView(){
        bookActivityView = null
    }

    fun saveReview(){
        // здесь должно быть что то и должен быть observer для состояния рейтинга, чтобы после добавления оценки модель обнавила рейтинг и количество ревью
    }


}