package com.e.bookstory.model.states

import com.e.bookstory.CurrentUser
import com.e.bookstory.entities.*
import com.e.bookstory.model.App
import com.e.bookstory.model.AppModelInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ModelState {

    abstract fun loadNextBooksPortion(): ArrayList<Book>
    abstract fun loadNextPurchasePortion(): ArrayList<Purchase>
    abstract fun loadNextOrdersPortion(): ArrayList<Order>
    abstract fun loadProfileInfo(): ProfileInfo
    abstract fun loadNextReviewsPortion(): ArrayList<Review>

    protected fun getNextTestOrdersPortion(): ArrayList<Order>{
        val orders: ArrayList<Order> = ArrayList()
        val books: ArrayList<Book> = ArrayList()
        val testBook: Book = Book()
        for(x in 0..3)books.add(testBook)
        val testOrder: Order = Order(0, "333", books)

        for(x in 0..20) {
            orders.add(testOrder)
        }
        return orders
    }

    protected fun getNextTestBooksPortion(): ArrayList<Book>{
        val books: ArrayList<Book> = ArrayList()
        val testBook: Book = Book()
        for(x in 0..9) books.add(testBook)
        return books
    }

    protected fun getNextTestPurchasesPortion(): ArrayList<Purchase>{
        val purchases: ArrayList<Purchase> = ArrayList()
        val testBook: Book = Book()
        for(x in 0..9) purchases.add(Purchase(1, testBook)) // это нужно будет заменить на подгрузку с сервера или БД
        return purchases
    }

    protected fun getTestProfile(): ProfileInfo{
        return ProfileInfo("Андрей", "Гилёв", "Васильевич", 20, "andrgilv", "andrgilv@gmail.com")
    }

    protected fun getNextTestReviewPortion(): ArrayList<Review>{
        val reviews: ArrayList<Review> = ArrayList()
        val review: Review =
            Review(
                "Гилёв Андрей Васильевич",
                444,
                "какой то текст ревью какой то текст ревью какой то текст ревью какой то текст ревью какой " +
                        "то текст ревью какой то текст ревью какой то текст ревью какой то текст ревью какой то текст ревью" +
                        " какой то текст ревью какой то текст ревью какой то текст ревью какой то текст ревью" +
                        " какой то текст ревью какой то текст ревью какой то текст ревью какой то текст ревью" +
                        " какой то текст ревью какой то текст ревью какой то текст ревью какой то текст ревью",
                9
            )
        for(i in 0..10) reviews.add(review)
        return reviews
    }

    protected fun getBookPageFromServer(page: Long, count: Long, typeSort: String, isRevert: Boolean){
        App.service.getBookPage(page, count, typeSort, isRevert).enqueue(object :
            Callback<BooksPage> {
            override fun onFailure(call: Call<BooksPage>, t: Throwable) {
                //Toast.makeText(this@CatalogActivity, "${t.message}", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<BooksPage>, response: Response<BooksPage>) {
                response.body()?.let {
                    AppModelInterface.books.addAll(it.content)
                }
                //notifyBookObservers()
            }
        })

    }

    protected fun getBooksFromCartFromServer(){
        App.service.getCartByLogin(CurrentUser.login).enqueue(object :
            Callback<java.util.ArrayList<Purchase>> {

            override fun onFailure(call: Call<java.util.ArrayList<Purchase>>, t: Throwable) {
                //Toast.makeText(this@CartActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<java.util.ArrayList<Purchase>>,
                response: Response<java.util.ArrayList<Purchase>>
            ) {
                response.body()?.let { AppModelInterface.purchases.addAll(it)}
                //notifyPurchaseObservers()
            }
        })

    }

    protected fun getOrdersFromServer(){
        App.service.getOrdersByLogin(CurrentUser.login).enqueue(object :
            Callback<java.util.ArrayList<Order>> {

            override fun onFailure(call: Call<java.util.ArrayList<Order>>, t: Throwable) {
                //Toast.makeText(this@OrdersActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<java.util.ArrayList<Order>>,
                response: Response<java.util.ArrayList<Order>>
            ) {
                response.body()?.let { AppModelInterface.orders.addAll(it)}
                //notifyOrderObservers()
            }
        })

    }

    protected fun getProfileInfoFromServer(){
        App.service.getProfilByLogin(CurrentUser.login).enqueue(object :
            Callback<ProfileInfo> {

            override fun onFailure(call: Call<ProfileInfo>, t: Throwable) {
                //Toast.makeText(this@ProfileActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ProfileInfo>,
                response: Response<ProfileInfo>
            ) {
                response.body()?.let { AppModelInterface.profileInfo = it}
                //notifyProfileInfoObservers()
            }
        })

    }

}