package com.e.bookstory.model.states

import com.e.bookstory.entities.*

class ModelStateNoInternetNoDB: ModelState() {
    override fun loadNextBooksPortion(): ArrayList<Book> {
        return getNextTestBooksPortion()
    }

    override fun loadNextPurchasePortion(): ArrayList<Purchase> {
        return getNextTestPurchasesPortion()
    }

    override fun loadNextOrdersPortion(): ArrayList<Order> {
        return  getNextTestOrdersPortion()
    }

    override fun loadProfileInfo(): ProfileInfo {
        return getTestProfile()
    }

    override fun loadNextReviewsPortion(): ArrayList<Review> {
        return  getNextTestReviewPortion()
    }
}