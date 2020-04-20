package com.e.bookstory.activityAdapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.e.bookstory.fragments.ProfileFragment
import com.e.bookstory.fragments.CartFragment
import com.e.bookstory.fragments.CatalogFragment
import com.e.bookstory.fragments.OrdersFragment


class PagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    private val catalogFragment: CatalogFragment = CatalogFragment()
    private val cartFragment: CartFragment = CartFragment()
    private val orderFragment: OrdersFragment = OrdersFragment()
    private val profileFragment: ProfileFragment = ProfileFragment()
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return catalogFragment
            1 -> return cartFragment
            2 -> return orderFragment
            3 -> return profileFragment
            else -> return Fragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }
}