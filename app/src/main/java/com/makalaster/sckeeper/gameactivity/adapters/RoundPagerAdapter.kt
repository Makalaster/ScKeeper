package com.makalaster.sckeeper.gameactivity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
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