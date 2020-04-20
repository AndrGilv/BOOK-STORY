package com.e.bookstory.activities

import android.R as androidR
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.viewpager.widget.ViewPager
import com.e.bookstory.BottomNavBar
import com.e.bookstory.CurrentUser
import com.e.bookstory.R
import com.e.bookstory.activities.MainActivityStaits.*
import com.e.bookstory.activities.views.MainActivityView
import com.e.bookstory.activityAdapters.PagerAdapter
import com.e.bookstory.presenters.MainActivityPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainActivityView {


    private lateinit var avatarImageView: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var presenter: MainActivityPresenter

    private val catalogState: State = CatalogState()
    private val cartState: State = CartState()
    private val ordersState: State = OrdersState()
    private val profileState: State = ProfileState()

    private var state: State = catalogState




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this)
        val pager: ViewPager = findViewById(R.id.pager)
        pager.adapter = PagerAdapter(this.supportFragmentManager)
        if(savedInstanceState != null){

        }

        val fab = findViewById<View>(R.id.buyButton) as FloatingActionButton
        fab.setOnClickListener { view ->
            run {

                Snackbar.make(view, "Заказ оформлен", Snackbar.LENGTH_SHORT)
                    .setAction("Отменить") {
                        Toast.makeText(this, "Заказ отменён", Toast.LENGTH_LONG).show()
                    }.show()
            }
        }

        this.window.statusBarColor = getColor(R.color.colorPrimaryDark)

        CurrentUser.login = "login"

        bottomNavigationView = findViewById(R.id.mainButtomAppBur)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.catalogItem -> {
                    //Toast.makeText(this, "catalogItem", Toast.LENGTH_LONG)
                    state = catalogState
                    pager.currentItem = 0
                    true
                }
                R.id.cartItem -> {
                    //Toast.makeText(this, "cartItem", Toast.LENGTH_LONG)
                    /*val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)*/
                    state = cartState
                    pager.currentItem = 1
                    true
                }
                R.id.ordersItem -> {
                    /*Toast.makeText(this, "orderItem", Toast.LENGTH_LONG)
                    val intent = Intent(this, OrdersActivity::class.java)
                    startActivity(intent)*/
                    state = ordersState
                    pager.currentItem = 2
                    true
                }
                R.id.profilItem -> {
                    /*Toast.makeText(this, "profileItem", Toast.LENGTH_LONG)
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)*/
                    state = profileState
                    pager.currentItem = 3
                    true
                }
                else -> throw Exception()
            }
            if(it.itemId == R.id.profilItem){
                mainSearchTextView.visibility = EditText.GONE
                mainFilterBtn.visibility = ImageView.GONE
                mainSortBtn.visibility = ImageView.GONE
                profilActivityTitleTV.visibility = View.VISIBLE
            }else{
                mainSearchTextView.visibility = EditText.VISIBLE
                mainFilterBtn.visibility = ImageView.VISIBLE
                mainSortBtn.visibility = ImageView.VISIBLE
                profilActivityTitleTV.visibility = View.GONE
            }
            if(it.itemId == R.id.cartItem){
                fab.visibility = View.VISIBLE
            }
            else{
                fab.visibility = View.GONE
            }
            true
        }
        bottomNavigationView.selectedItemId = intent.getIntExtra("menuItemId", R.id.catalogItem)

        BottomNavBar.initialise(bottomNavigationView)

        avatarImageView = findViewById(R.id.mainAvatarImageView) as ImageView
        Picasso.get()
            .load(R.drawable.photo)//http://developer.alexanderklimov.ru/android/images/android_cat.jpg
            .transform(CropCircleTransformation())
            .into(avatarImageView)

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> bottomNavigationView.selectedItemId =
                        R.id.catalogItem
                    1 -> bottomNavigationView.selectedItemId =
                        R.id.cartItem
                    2 -> bottomNavigationView.selectedItemId =
                        R.id.ordersItem
                    3 -> bottomNavigationView.selectedItemId =
                        R.id.profilItem
                    else -> throw Exception()

                }
            }

        })
        presenter.attachView(this)

        mainFilterBtn.setOnClickListener {
            state.filterBtnClicked(this)

        }

        mainSortBtn.setOnClickListener {
            state.sortBtnClicked(this)

        }
    }

    override fun onRestart() {
        super.onRestart()
        BottomNavBar.initialise(bottomNavigationView)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
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
            super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }
}


