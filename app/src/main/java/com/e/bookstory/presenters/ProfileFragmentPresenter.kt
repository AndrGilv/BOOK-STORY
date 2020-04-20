package com.e.bookstory.presenters

import android.content.Context
import com.e.bookstory.model.AppModel
import com.e.bookstory.model.AppModelInterface
import com.e.bookstory.entities.ProfileInfo
import com.e.bookstory.fragments.views.ProfileFragmentView
import com.e.bookstory.observers.ProfileObserver

class ProfileFragmentPresenter(context: Context): Presenter(context), ProfileObserver {
    private var profileFragmentView: ProfileFragmentView? = null

    val user: ProfileInfo
        get() {return model.getProfile()}

    var loading: Boolean = false

    init {
        model.registerProfileInfoObservers(this)
    }

    fun attachView(profileFragmentView: ProfileFragmentView){
        this.profileFragmentView = profileFragmentView;
    }

    fun detachView(){
        profileFragmentView = null
    }

    fun getProfileInfo(){
        loading = true

        model.loadProfileInfo()
        //update()

        loading = false
    }

    override fun update() {
        profileFragmentView?.showProfileInfo()
    }
}