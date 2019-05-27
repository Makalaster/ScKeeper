package com.makalaster.sckeeper.gameactivity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.makalaster.data.models.Round
import com.makalaster.sckeeper.gameactivity.RoundFragment

class RoundPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    private val roundsList = arrayListOf<Round>()

    override fun getItem(position: Int): Fragment {
        return RoundFragment.newInstance(roundsList[position].roundNumber)
    }

    override fun getCount(): Int {
        return roundsList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (roundsList[position].roundNumber == Round.TOTALS_ROUND) "Totals" else "Round ${position + 1}"
    }

    fun setRounds(rounds: List<Round>) {
        roundsList.clear()

        for (i in rounds.indices) {
            roundsList.add(rounds[i])
        }

        if (rounds.size > 1)
            roundsList.add(Round(Round.TOTALS_ROUND))

        notifyDataSetChanged()
    }
}