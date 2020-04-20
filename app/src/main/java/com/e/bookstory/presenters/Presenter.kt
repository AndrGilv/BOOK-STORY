package com.e.bookstory.presenters

import android.content.Context
import com.e.bookstory.View
import com.e.bookstory.model.AppModel
import com.e.bookstory.model.AppModelInterface

abstract class Presenter(context: Context) {
    protected var model: AppModelInterface =
        AppModel(context)
}