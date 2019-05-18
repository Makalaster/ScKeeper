package com.makalaster.sckeeper.gameactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.makalaster.sckeeper.R
import com.makalaster.sckeeper.gameactivity.adapters.RoundPagerAdapter

import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    private lateinit var roundPager: ViewPager
    private lateinit var roundTabs: TabLayout
    private val pagerAdapter = RoundPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        setSupportActionBar(toolbar)

        roundPager = findViewById(R.id.round_pager)
        roundTabs = findViewById(R.id.round_tabs)

        roundTabs.setupWithViewPager(roundPager)
        roundPager.adapter = pagerAdapter

        pagerAdapter.addRound(RoundFragment.newInstance())
        pagerAdapter.addRound(RoundFragment.newInstance())

        fab.setOnClickListener {
            pagerAdapter.addRound(RoundFragment.newInstance())
        }
    }
}
