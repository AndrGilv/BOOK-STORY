package com.e.bookstory.uslessActivities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.e.bookstory.model.App
import com.e.bookstory.CurrentUser
import com.e.bookstory.R
import com.e.bookstory.entities.ProfileInfo
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class ProfileActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var avatarImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val toolBar: Toolbar = findViewById(R.id.profileTopAppBar)
        setSupportActionBar(toolBar)

        avatarImageView = findViewById(R.id.avatarImageView) as ImageView
        Picasso.get()
            .load(R.drawable.photo)//http://developer.alexanderklimov.ru/android/images/android_cat.jpg
            .transform(CropCircleTransformation())
            .into(avatarImageView)
        bottomNavigationView = findViewById(R.id.catalogButtomAppBur) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.cartItem -> {
                    Toast.makeText(this, "cartItem", Toast.LENGTH_LONG)
                    val intent = Intent(this, CatalogActivity::class.java)
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
                    val intent = Intent(this, OrdersActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.profilItem -> {
                    Toast.makeText(this, "profileItem", Toast.LENGTH_LONG)

                    true
                }
                else -> throw Exception()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.profilItem
    }

    fun getProfilInfo(){
        App.service.getProfilByLogin(CurrentUser.login).enqueue(object :
            Callback<ProfileInfo> {

            override fun onFailure(call: Call<ProfileInfo>, t: Throwable) {
                Toast.makeText(this@ProfileActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ProfileInfo>,
                response: Response<ProfileInfo>
            ) {
                response.body()?.let {
                    secondNameInputText.setText(it.lastName)
                    firstNameInputText.setText(it.firstName)
                    middleNameInputText.setText(it.middleName)
                    emailInputText.setText(it.email)
                    phoneInputText.setText("поля телефон нет в базе")
                    ageInputText.setText(it.age)
                }
            }
        })

    }
}
