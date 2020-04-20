package com.e.bookstory

import com.e.bookstory.entities.ProfileInfo
import com.google.gson.annotations.SerializedName

object CurrentUser {
    var login: String = ""
    var id: Int = 0

    var firstName: String = ""
    var lastName: String = ""
    var middleName: String= ""
    var age: Int = -1
    var email: String = ""

    fun getProfileInfo(): ProfileInfo{
        return ProfileInfo(
            firstName,
            lastName,
            middleName,
            age,
            login,
            email)
    }
}