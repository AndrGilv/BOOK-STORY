package com.e.bookstory.uslessActivities


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.*
import com.e.bookstory.activityAdapters.CatalogRVAdapter
import com.e.bookstory.entities.Book
import com.e.bookstory.entities.BooksPage
import com.e.bookstory.model.App
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class CatalogActivity : AppCompatActivity() {

    private var books: ArrayList<Book> = ArrayList()
    private lateinit var rv: RecyclerView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var avatarImageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
        val window: Window = this.getWindow() // clear FLAG_TRANSLUCENT_STATUS

        window.setStatusBarColor(getColor(R.color.colorPrimaryDark))

        CurrentUser.login = "login"


        rv = findViewById(R.id.catalogRView)
        val llm = LinearLayoutManager(this)
        rv.layoutManager = llm
        val adapter: CatalogRVAdapter =
            CatalogRVAdapter(books, this)
        rv.setAdapter(adapter)

        bottomNavigationView = findViewById(R.id.catalogButtomAppBur)
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

        avatarImageView = findViewById(R.id.catalogAvatarImageView) as ImageView
        Picasso.get()
            .load(R.drawable.photo)//http://developer.alexanderklimov.ru/android/images/android_cat.jpg
            .transform(CropCircleTransformation())
            .into(avatarImageView)

        val testBook: Book = Book()
        for(x in 0..10) books.add(testBook)
    }



    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.catalogItem
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.catalog_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {
            // User chose the "Print" item
            Toast.makeText(this,"set1",Toast.LENGTH_LONG).show()
            true
        }
        R.id.action_settings ->{
            Toast.makeText(this,"set2",Toast.LENGTH_LONG).show()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    fun getBookPage(page: Long, count: Long, typeSort: String, isRevert: Boolean){
        App.service.getBookPage(page, count, typeSort, isRevert).enqueue(object : Callback<BooksPage> {

            override fun onFailure(call: Call<BooksPage>, t: Throwable) {
                Toast.makeText(this@CatalogActivity, "${t.message}", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<BooksPage>, response: Response<BooksPage>) {
                response.body()?.let { books = it.content }
            }
        })

    }



}
