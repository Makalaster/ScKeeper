package com.makalaster.sckeeper.gameactivity

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makalaster.data.models.Player

import com.makalaster.sckeeper.R
import com.makalaster.sckeeper.gameactivity.adapters.PlayerRecyclerAdapter
import com.makalaster.sckeeper.gameactivity.adapters.PlayerScoreTableViewHolder
import com.makalaster.widgets.ScoreBoxListener
import kotlinx.android.synthetic.main.round_fragment.*

class RoundFragment : Fragment(), ScoreBoxListener,
    PlayerScoreTableViewHolder.AddPlayerViewHolder.OnAddPlayerClickedListener {

    private lateinit var adapter: PlayerRecyclerAdapter
    private lateinit var viewModel: RoundViewModel

    companion object {
        private var playerNumber = 0

        fun newInstance() = RoundFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.round_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PlayerRecyclerAdapter(this, this)

        player_list.adapter = adapter
        player_list.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            initViewModel(it.application)
        }
    }

    private fun initViewModel(application: Application) {
        viewModel = ViewModelProviders.of(this, RoundViewModelFactory(application))
            .get(RoundViewModel::class.java)

        viewModel.getPlayers().observe(this, Observer {
            it.let {playerList ->
                adapter.setList(playerList)
            }
        })
    }

    override fun onAddPlayerClicked() {
        viewModel.addPlayer(Player("Player ${adapter.itemCount}", ++playerNumber))
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
