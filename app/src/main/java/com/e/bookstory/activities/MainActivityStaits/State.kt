package com.e.bookstory.activities.MainActivityStaits

import android.content.Context

interface State {
    fun sortBtnClicked(context: Context)
    fun filterBtnClicked(context: Context)
}