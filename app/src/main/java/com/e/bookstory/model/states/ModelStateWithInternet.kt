package com.e.bookstory.model.states

import android.util.Log
import com.e.bookstory.entities.*

class ModelStateWithInternet: ModelState() {
    override fun loadNextBooksPortion(): ArrayList<Book> {
        Log.i("mylog", "loadNextBooksPortion from internet saving in db")
        return ArrayList()
    }

    override fun loadNextPurchasePortion(): ArrayList<Purchase> {
        Log.i("mylog", "loadNextPurchasePortion from internet saving in db")
        return ArrayList()
    }

    override fun loadNextOrdersPortion(): ArrayList<Order> {
        Log.i("mylog", "loadNextOrdersPortion from internet saving in db")
        return ArrayList()
    }

    override fun loadProfileInfo(): ProfileInfo {
        Log.i("mylog", "loadProfileInfo from internet saving in db")
        return ProfileInfo("", "", "", 1, "", "" )
    }

    override fun loadNextReviewsPortion(): ArrayList<Review> {
        Log.i("mylog", "loadNextReviewsPortion from internet saving in db")
        return ArrayList()
    }
}