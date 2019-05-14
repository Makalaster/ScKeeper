package com.makalaster.sckeeper.gameactivity.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.makalaster.sckeeper.gameactivity.RoundFragment

class RoundPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val mRoundsList = arrayListOf<RoundFragment>()

    override fun getItem(position: Int): Fragment {
        return mRoundsList[position]
    }

    override fun getCount(): Int {
        return mRoundsList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Round ${position + 1}"
    }

    fun addRound(newRound: RoundFragment) {
        mRoundsList.add(newRound)
        notifyDataSetChanged()
    }
}