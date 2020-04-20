package com.e.bookstory.model

import com.e.bookstory.entities.*
import com.e.bookstory.observers.*
import kotlin.collections.ArrayList


abstract class AppModelInterface {

    abstract fun closeDB()

    fun registerBookObservers(booksObserver: BooksObserver){
        booksObservers.add(booksObserver)
    }
    fun removeBookObservers(booksObserver: BooksObserver){
        booksObservers.remove(booksObserver)
    }
    fun notifyBookObservers() {
        for(booksObserver: BooksObserver in booksObservers){
            booksObserver.update()
        }
    }

    fun registerPurchaseObservers(purchaseObserver: PurchaseObserver) {
        purchaseObservers.add(purchaseObserver)
    }

    fun removePurchaseObservers(purchaseObserver: PurchaseObserver) {
        purchaseObservers.remove(purchaseObserver)
    }

    fun notifyPurchaseObservers() {
        for(purchaseObserver: PurchaseObserver in purchaseObservers){
            purchaseObserver.update()
        }
    }

    fun registerOrderObservers(orderObserver: OrderObserver) {
        orderObservers.add(orderObserver)
    }

    fun removeOrderObservers(orderObserver: OrderObserver) {
        orderObservers.remove(orderObserver)
    }

    fun notifyOrderObservers() {
        for(orderObserver: OrderObserver in orderObservers){
            orderObserver.update()
        }
    }

    fun registerProfileInfoObservers(profileObserver: ProfileObserver) {
        profileObservers.add(profileObserver)
    }

    fun removeProfileInfoObservers(profileObserver: ProfileObserver) {
        profileObservers.remove(profileObserver)
    }

    fun notifyProfileInfoObservers() {
        for(profileObserver: ProfileObserver in profileObservers){
            profileObserver.update()
        }
    }

    fun registerReviewObservers(reviewObserver: ReviewObserver) {
        reviewObservers.add(reviewObserver)
    }

    fun removeReviewObservers(reviewObserver: ReviewObserver) {
        reviewObservers.remove(reviewObserver)
    }

    fun notifyReviewObservers() {
        for(reviewObserver: ReviewObserver in reviewObservers){
            reviewObserver.update()
        }
    }

    abstract fun loadNextBooksPortion()
    abstract fun loadNextPurchasePortion()
    abstract fun loadNextOrdersPortion()
    abstract fun loadProfileInfo()
    abstract fun loadNextReviewsPortion()

    fun getBooks(): ArrayList<Book>{
        return books
    }
    fun getPurchases(): ArrayList<Purchase>{
        return purchases
    }
    fun getOrders(): ArrayList<Order>{
        return orders
    }
    fun getProfile(): ProfileInfo{
        return profileInfo
    }
    fun getReviews(): ArrayList<Review>{
        return reviews
    }

    companion object{

        private val booksObservers: ArrayList<BooksObserver> = ArrayList()
        private val purchaseObservers: ArrayList<PurchaseObserver> = ArrayList()
        private val orderObservers: ArrayList<OrderObserver> = ArrayList()
        private val profileObservers: ArrayList<ProfileObserver> = ArrayList()
        private val reviewObservers: ArrayList<ReviewObserver> = ArrayList()

        val books: ArrayList<Book> = ArrayList()

        val purchases: ArrayList<Purchase> = ArrayList()

        val orders: ArrayList<Order> = ArrayList()

        var profileInfo: ProfileInfo = ProfileInfo("", "", "", -1, "", "")

        val reviews: ArrayList<Review> = ArrayList()
    }
}