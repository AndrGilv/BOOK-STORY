package com.e.bookstory.entities

import com.google.gson.annotations.SerializedName

data class Purchase(@SerializedName("count") var count: Int,
                    @SerializedName("book") var book: Book) {
}