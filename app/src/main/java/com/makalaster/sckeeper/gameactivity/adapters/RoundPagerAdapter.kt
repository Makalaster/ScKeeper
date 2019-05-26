package com.makalaster.sckeeper.gameactivity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.makalaster.data.models.Round
import com.makalaster.sckeeper.gameactivity.RoundFragment

class RoundPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val roundsList = arrayListOf<RoundFragment>()

    override fun getItem(position: Int): Fragment {
        return roundsList[position]
    }

    override fun getCount(): Int {
        return roundsList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Round ${position + 1}"
    }

    fun setRounds(rounds: List<Round>) {
        roundsList.clear()

        for (i in rounds.indices) {
            roundsList.add(RoundFragment.newInstance(i))
        }

        notifyDataSetChanged()
    }
}