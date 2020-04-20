package com.e.bookstory.uslessActivities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.*
import com.e.bookstory.activityAdapters.CartRVAdapter
import com.e.bookstory.entities.Book
import com.e.bookstory.entities.Purchase
import com.e.bookstory.model.App
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.net.URL
import java.util.*


class CartActivity : AppCompatActivity() {
    private var purchases: ArrayList<Purchase> = ArrayList()
    private lateinit var rv: RecyclerView
    private lateinit var avatarImageView: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val toolBar: Toolbar = findViewById(R.id.cartTopAppBar)
        setSupportActionBar(toolBar)

        avatarImageView = findViewById(R.id.cartAvatarImageView) as ImageView
        Picasso.get()
            .load(R.drawable.photo)
            .transform(CropCircleTransformation())
            .into(avatarImageView)

        bottomNavigationView = findViewById(R.id.catalogButtomAppBur) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.cartItem -> {
                    Toast.makeText(this, "cartItem", Toast.LENGTH_LONG)

                    true
                }
                R.id.catalogItem -> {
                    Toast.makeText(this, "catalogItem", Toast.LENGTH_LONG)
                    val intent = Intent(this, CatalogActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.ordersItem -> {
                    Toast.makeText(this, "orderItem", Toast.LENGTH_LONG)
                    val intent = Intent(this, OrdersActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.profilItem -> {
                    Toast.makeText(this, "profileItem", Toast.LENGTH_LONG)
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> throw Exception()
            }
        }

        //getBooksFromCart()

        rv = findViewById(R.id.cartRView)
        val llm = LinearLayoutManager(this)
        rv.layoutManager = llm
        val adapter: CartRVAdapter =
            CartRVAdapter(purchases, this)
        rv.setAdapter(adapter)

        val testBook: Book =
            Book(
                1,
                "Книга-хуига",
                "Автор Хуявтор",
                777,
                100500,
                "Едитор хуитор",
                "Мягкая",
                50.0,
                "Фантастика",
                "Издатель хуятель",
                "Серия хуерия",
                "Переводчик хуётчик",
                "Пендосский",
                222,
                20,
                20,
                20,
                3,
                URL("https://kotlinlang.org/"),
                "Какое-то описание просто ну капец длинное описание книги, никогда токого небыло и вот опять просто пиздец"
            )
        for(x in 0..10) purchases.add(Purchase(7, testBook))
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.cartItem
    }

    fun getBooksFromCart(){
        App.service.getCartByLogin(CurrentUser.login).enqueue(object :
            Callback<ArrayList<Purchase>> {

            override fun onFailure(call: Call<ArrayList<Purchase>>, t: Throwable) {
                Toast.makeText(this@CartActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ArrayList<Purchase>>,
                response: Response<ArrayList<Purchase>>
            ) {
                response.body()?.let { purchases = it }
            }
        })

    }
}
