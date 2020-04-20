package com.e.bookstory.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log
import com.e.bookstory.network.NetworkUtil
import com.e.bookstory.model.states.*
import com.e.bookstory.observers.NetworkObserver

class AppModel(context: Context): AppModelInterface(), NetworkObserver {
    private val context = context
    private val databaseHelper: DatabaseHelper
    private var db: SQLiteDatabase? = null

    private val NO_INTERNET_NO_DB_MODEL_STATE: ModelStateNoInternetNoDB = ModelStateNoInternetNoDB()
    private val WITH_INTERNET_WITH_DB_MODEL_STATE: ModelStateWithInternet = ModelStateWithInternet()
    private val WITH_INTERNET_NO_DB_MODEL_STATE: ModelStateWithInternetNoDB = ModelStateWithInternetNoDB()
    private val NO_INTERNET_WITH_DB_MODEL_STATE: ModelStateNoInternet = ModelStateNoInternet()

    private var modelState: ModelState = NO_INTERNET_NO_DB_MODEL_STATE

    private var dbAvailable = false

    init{
        databaseHelper = DatabaseHelper(context)

        val testState = true

        try {
            db = databaseHelper.writableDatabase
            dbAvailable = true
        }
        catch ( e: SQLiteException){
            Log.e("mylog", "database unavailable")
        }

        updateNetworkState(NetworkUtil.getConnectivityStatus(context))

        if(testState){
            modelState = NO_INTERNET_NO_DB_MODEL_STATE
        }
    }

    override fun closeDB() {
        db?.close()
    }

    override fun loadNextOrdersPortion(){
        orders.addAll(modelState.loadNextOrdersPortion())
        notifyOrderObservers()
    }

    override fun loadNextReviewsPortion() {
        reviews.addAll(modelState.loadNextReviewsPortion())
        notifyReviewObservers()
    }

    override fun loadProfileInfo() {
        profileInfo = modelState.loadProfileInfo()
        notifyProfileInfoObservers()
    }

    override fun loadNextBooksPortion(){
        books.addAll(modelState.loadNextBooksPortion())
        notifyBookObservers()
    }

    override fun loadNextPurchasePortion() {
        purchases.addAll(modelState.loadNextPurchasePortion())
        notifyPurchaseObservers()
    }

    override fun updateNetworkState(newState: Int) {
        if(newState == NetworkUtil.NETWORK_STATUS_MOBILE || newState == NetworkUtil.NETWORK_STATUS_WIFI){
            if (dbAvailable){
                modelState = WITH_INTERNET_WITH_DB_MODEL_STATE
            }
            else{
                modelState = WITH_INTERNET_NO_DB_MODEL_STATE
            }
        }
        else{
            if (dbAvailable){
                modelState = NO_INTERNET_WITH_DB_MODEL_STATE
            }
            else{
                modelState = NO_INTERNET_NO_DB_MODEL_STATE
            }
        }
    }
}