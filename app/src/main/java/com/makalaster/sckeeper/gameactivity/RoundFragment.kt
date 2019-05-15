package com.makalaster.sckeeper.gameactivity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.makalaster.sckeeper.R
import com.makalaster.sckeeper.gameactivity.adapters.PlayerRecyclerAdapter
import com.makalaster.widgets.ScoreBoxListener
import kotlinx.android.synthetic.main.round_fragment.*

class RoundFragment : Fragment(), ScoreBoxListener {

    companion object {
        fun newInstance() = RoundFragment()
    }

    private lateinit var viewModel: RoundViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.round_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player_list.adapter = PlayerRecyclerAdapter(this)
        player_list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, RoundViewModelFactory())
            .get(RoundViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDecrementTap() {

    }

    override fun onDecrementLongPress(): Boolean {
        return true
    }

    override fun onIncrementTap() {

    }

    override fun onIncrementLongPress(): Boolean {
        return true
    }

    override fun onScoreDisplayTap() {

    }

    override fun onScoreDisplayLongPress(): Boolean {
        return true
    }
}
