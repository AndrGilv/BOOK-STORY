package com.e.bookstory.model

import com.e.bookstory.entities.*
import retrofit2.Call
import retrofit2.http.*

interface Service {

    val languageSort: String //какие ещё бывают типы сортироваки
        get() = "language"

    @GET("api/order/{login}")
    fun getOrdersByLogin(@Path("login") login : String): Call<ArrayList<Order>>

    @GET("api/customer/profile/{login}")
    fun getProfilByLogin(@Path("login") login : String): Call<ProfileInfo>

    @GET("api/cart/{login}")
    fun getCartByLogin(@Path("login") login : String): Call<ArrayList<Purchase>>

    @GET("api/book/{id}")
    fun getBookById(@Path("id") id : Long): Call<Book> //хз зачем мне этот запрос

    @Headers(
        "Content-Type: application/json"
    )
    @GET("api/book")
    fun getBookPage(@Body page : Long, @Body count: Long, @Body typeSort: String, @Body isRevert: Boolean): Call<BooksPage>



/*
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<Unit>
    @POST("users")
    @FormUrlEncoded
    fun createUser(@Body user: Unit): Call<Unit>
    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Unit>
*/

}