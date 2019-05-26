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
import kotlinx.android.synthetic.main.round_fragment.*

class RoundFragment : Fragment(), PlayerScoreTableViewHolder.AddPlayerViewHolder.OnAddPlayerClickedListener {

    private lateinit var adapter: PlayerRecyclerAdapter
    private lateinit var viewModel: RoundViewModel

    private val paramBundle = Bundle()

    companion object {
        private const val ROUND_NUMBER = "round number"

        fun newInstance(roundNumber: Int): RoundFragment {
            val fragment = RoundFragment()

            val args = Bundle()
            args.putInt(ROUND_NUMBER, roundNumber)

            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            paramBundle.putInt(ROUND_NUMBER, it.getInt(ROUND_NUMBER))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.round_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            initViewModel(it.application)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadRound()
    }

    private fun initViewModel(application: Application) {
        viewModel = ViewModelProviders.of(this, RoundViewModelFactory(application, paramBundle.getInt(ROUND_NUMBER)))
            .get(RoundViewModel::class.java)

        viewModel.players.observe(this, Observer {
            it.let { playerList ->
                adapter.updatePlayers(playerList)
            }
        })

        viewModel.round.observe(this, Observer { round ->
            round?.let {
                adapter.updateRound(it)
            }
        })

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = PlayerRecyclerAdapter(viewModel, this)

        player_list.adapter = adapter
        player_list.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }

    override fun onAddPlayerClicked() {
        viewModel.addPlayer(Player(adapter.itemCount,"Player ${adapter.itemCount}"))
    }
}
