package com.e.bookstory.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.e.bookstory.observers.NetworkObserver

class NetworkChangeReceiver(): BroadcastReceiver() {

    private val observers: ArrayList<NetworkObserver> = ArrayList()

    override fun onReceive(context: Context?, intent: Intent?) {


        if ("android.net.conn.CONNECTIVITY_CHANGE" == intent!!.action) {
            /*if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
                //ForceExitPause(context).execute()
            } else {
                //ResumeForceExitPause(context).execute()
            }*/
            Log.e("mylog", "Network connection changed")
            val status =
                NetworkUtil.getConnectivityStatusString(
                    context!!
                )
            notifyNetworkObserver(status)
        }
    }

    fun registerNetworkObserver(networkObserver: NetworkObserver){
        observers.add(networkObserver)
    }
    fun removeNetworkObserver(networkObserver: NetworkObserver){
        observers.remove(networkObserver)
    }
    private fun notifyNetworkObserver(newState: Int) {
        for(networkObserver: NetworkObserver in observers){
            networkObserver.updateNetworkState(newState)
        }
    }
}