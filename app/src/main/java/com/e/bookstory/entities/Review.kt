package com.e.bookstory.entities

import com.google.gson.annotations.SerializedName
import java.util.*

class Review(@SerializedName("author") var author: String,
             @SerializedName("date") var date: Int,
             @SerializedName("text") var text: String,
             @SerializedName("rating") var rating: Int) {
    /*var author: String
    var date: Date
    var text: String
    var rating: Int

    init{
        this.author = author
        this.date = date
        this.rating = rating
        this.text = text
    }*/
}