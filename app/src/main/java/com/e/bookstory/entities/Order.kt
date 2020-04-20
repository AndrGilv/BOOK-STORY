package com.e.bookstory.entities

import com.e.bookstory.entities.Book
import com.google.gson.annotations.SerializedName
import java.util.*

data class Order(@SerializedName("orderId") var orderId: Int,
                 @SerializedName("date") var date: String,
                 @SerializedName("order") var books: ArrayList<Book>) {
    /*var orderId: Int
    var date: Date
    var books: ArrayList<Book>

    init {
        this.orderId = orderId
        this.date = date
        this.books = books
    }*/

}