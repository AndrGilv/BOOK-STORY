package com.e.bookstory.model.states

import android.util.Log
import com.e.bookstory.entities.*

class ModelStateNoInternet: ModelState() {
    override fun loadNextBooksPortion(): ArrayList<Book> {
        Log.i("mylog", "loadNextBooksPortion without internet from db")
        return ArrayList()
    }

    override fun loadNextPurchasePortion(): ArrayList<Purchase> {
        Log.i("mylog", "loadNextPurchasePortion without internet from db")
        return ArrayList()
    }

    override fun loadNextOrdersPortion(): ArrayList<Order> {
        Log.i("mylog", "loadNextOrdersPortion without internet from db")
        return ArrayList()
    }

    override fun loadProfileInfo(): ProfileInfo {
        Log.i("mylog", "loadProfileInfo without internet from db")
        return ProfileInfo("", "", "", 1, "", "" )
    }

    override fun loadNextReviewsPortion(): ArrayList<Review> {
        Log.i("mylog", "loadNextReviewsPortion without internet from db")
        return ArrayList()
    }
}