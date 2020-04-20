package com.e.bookstory.entities

import com.google.gson.annotations.SerializedName

data class ProfileInfo(@SerializedName("firstName") var firstName: String,
                       @SerializedName("lastName") var lastName: String,
                       @SerializedName("middleName") var middleName: String,
                       @SerializedName("age") var age: Int,
                       @SerializedName("login") var login: String,
                       @SerializedName("email") var email: String) {
}