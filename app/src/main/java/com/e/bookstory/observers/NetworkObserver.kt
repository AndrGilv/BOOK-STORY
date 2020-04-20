package com.e.bookstory.observers

interface NetworkObserver {
    fun updateNetworkState(newState: Int)
}