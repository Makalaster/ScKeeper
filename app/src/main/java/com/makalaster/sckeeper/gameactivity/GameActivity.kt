package com.makalaster.sckeeper.gameactivity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.makalaster.sckeeper.R
import com.makalaster.sckeeper.gameactivity.adapters.RoundPagerAdapter

import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    private lateinit var mRoundPager: ViewPager
    private lateinit var mRoundTabs: TabLayout
    private val mPagerAdapter = RoundPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        setSupportActionBar(toolbar)

        mRoundPager = findViewById(R.id.round_pager)
        mRoundTabs = findViewById(R.id.round_tabs)

        mRoundTabs.setupWithViewPager(mRoundPager)
        mRoundPager.adapter = mPagerAdapter

        mPagerAdapter.addRound(RoundFragment.newInstance())
        mPagerAdapter.addRound(RoundFragment.newInstance())

        fab.setOnClickListener {
            mPagerAdapter.addRound(RoundFragment.newInstance())
        }
    }
}
