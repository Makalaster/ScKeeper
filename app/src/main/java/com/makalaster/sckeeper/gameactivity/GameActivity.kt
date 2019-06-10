package com.makalaster.sckeeper.gameactivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.makalaster.data.models.Round
import com.makalaster.sckeeper.R
import com.makalaster.sckeeper.gameactivity.adapters.RoundPagerAdapter

import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    private val pagerAdapter = RoundPagerAdapter(supportFragmentManager)

    private lateinit var viewModel: GameActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        setSupportActionBar(toolbar)

        round_tabs.setupWithViewPager(round_pager)
        round_pager.adapter = pagerAdapter

        fab.setOnClickListener {
            viewModel.addRound(Round(pagerAdapter.count))
        }

        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let { menuItem ->
            when (menuItem.itemId) {
                R.id.action_clear_all -> viewModel.clearAll()
                R.id.action_clear_rounds -> viewModel.clearRounds()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(GameActivityViewModel::class.java)

        viewModel.rounds.observe(this, Observer { rounds ->
            rounds?.let { roundsList ->
                if (roundsList.isEmpty()) {
                    viewModel.addRound(Round(0))
                }

                totals_bottom_sheet.visibility = if (roundsList.size > 1) View.VISIBLE else View.GONE

                pagerAdapter.setRounds(roundsList)
            }
        })

        viewModel.roundAdded.observe(this, Observer {
            round_pager.setCurrentItem(pagerAdapter.count -1, true)
        })
    }
}
