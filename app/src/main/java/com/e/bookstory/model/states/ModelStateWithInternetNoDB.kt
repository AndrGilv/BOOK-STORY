package com.e.bookstory.model.states

import android.util.Log
import com.e.bookstory.entities.*

class ModelStateWithInternetNoDB: ModelState() {
    override fun loadNextBooksPortion(): ArrayList<Book> {
        Log.i("mylog", "loadNextBooksPortion from internet without db")
        return ArrayList()
    }

    override fun loadNextPurchasePortion(): ArrayList<Purchase> {
        Log.i("mylog", "loadNextPurchasePortion from internet without db")
        return ArrayList()
    }

    override fun loadNextOrdersPortion(): ArrayList<Order> {
        Log.i("mylog", "loadNextOrdersPortion from internet without db")
        return ArrayList()
    }

    override fun loadProfileInfo(): ProfileInfo {
        Log.i("mylog", "loadProfileInfo from internet without db")
        return ProfileInfo("", "", "", 1, "", "" )
    }

    override fun loadNextReviewsPortion(): ArrayList<Review> {
        Log.i("mylog", "loadNextReviewsPortion from internet without db")
        return ArrayList()
    }
}