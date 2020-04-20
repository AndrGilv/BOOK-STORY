package com.e.bookstory.uslessActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.*
import com.e.bookstory.activityAdapters.OrdersRVAdapter
import com.e.bookstory.entities.Book
import com.e.bookstory.entities.Order
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

class OrdersActivity : AppCompatActivity() {

    private var orders: ArrayList<Order> = ArrayList()
    private lateinit var rv: RecyclerView
    private lateinit var avatarImageView: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        val toolBar: Toolbar = findViewById(R.id.ordersTopAppBar)
        setSupportActionBar(toolBar)

        //getOrders()

        avatarImageView = findViewById(R.id.ordersAvatarImageView) as ImageView
        Picasso.get()
            .load(R.drawable.photo)
            .transform(CropCircleTransformation())
            .into(avatarImageView)

        bottomNavigationView = findViewById(R.id.catalogButtomAppBur) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.cartItem -> {
                    Toast.makeText(this, "cartItem", Toast.LENGTH_LONG)
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)
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

        rv = findViewById(R.id.ordersRView)
        val llm = LinearLayoutManager(this)
        rv.layoutManager = llm
        val adapter: OrdersRVAdapter =
            OrdersRVAdapter(orders, this)
        rv.adapter = adapter

        val books: ArrayList<Book> = ArrayList()
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
        for(x in 0..3)books.add(testBook)
        val testOrder: Order =
            Order(0, "333", books)

        for(x in 0..3) {
            orders.add(testOrder)
        }

    }
    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.ordersItem
    }

    fun getOrders(){
        App.service.getOrdersByLogin(CurrentUser.login).enqueue(object :
            Callback<ArrayList<Order>> {

            override fun onFailure(call: Call<ArrayList<Order>>, t: Throwable) {
                Toast.makeText(this@OrdersActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ArrayList<Order>>,
                response: Response<ArrayList<Order>>
            ) {
                response.body()?.let { orders = it }
            }
        })

    }
}
