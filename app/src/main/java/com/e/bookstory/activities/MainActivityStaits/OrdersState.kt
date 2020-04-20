package com.e.bookstory.activities.MainActivityStaits

import android.content.Context
import android.widget.Toast

class OrdersState: State {
    override fun sortBtnClicked(context: Context) {
        Toast.makeText(context,"sortBtn : orders", Toast.LENGTH_LONG).show()
    }

    override fun filterBtnClicked(context: Context) {
        Toast.makeText(context,"filterBtn : orders", Toast.LENGTH_LONG).show()
    }
}