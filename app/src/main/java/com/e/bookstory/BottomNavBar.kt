package com.e.bookstory

import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavBar {
    private var buttomNavigationView: BottomNavigationView


    companion object {
        private var buttomNavBar: BottomNavBar? = null
        private var num: Int = 0
        fun initialise(buttomNavigationView: BottomNavigationView): BottomNavBar? {

                buttomNavBar = BottomNavBar(buttomNavigationView)
                if (num > 0){
                    buttomNavigationView.getOrCreateBadge(R.id.cartItem).number = num
                }
            return buttomNavBar
        }

        fun getInstance(): BottomNavBar? {

            return buttomNavBar
        }

    }

    private constructor(buttomNavigationBar: BottomNavigationView){
        this.buttomNavigationView = buttomNavigationBar
    }

    fun incrementCartBadge(){
        buttomNavigationView.getOrCreateBadge(R.id.cartItem).number = ++num
    }

    fun decrementCartBadge(){
        if(num > 1){
            buttomNavigationView.getOrCreateBadge(R.id.cartItem).number = --num
        }
        else if(num == 1){
            num--
            buttomNavigationView.removeBadge(R.id.cartItem)
        }

    }
}