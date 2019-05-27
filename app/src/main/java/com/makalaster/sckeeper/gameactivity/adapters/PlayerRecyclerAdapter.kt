package com.makalaster.sckeeper.gameactivity.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.makalaster.data.models.Player
import com.makalaster.data.models.Round
import com.makalaster.sckeeper.R
import com.makalaster.widgets.ScoreBoxListener

class PlayerRecyclerAdapter(private val scoreBoxListener: ScoreBoxListener,
                            private val listener: PlayerScoreTableViewHolder.AddPlayerViewHolder.OnAddPlayerClickedListener):
    ListAdapter<Player, PlayerScoreTableViewHolder>(DIFF_CALLBACK) {

    private var round: Round = Round(0)
    private val playerZero = Player(0, ADD_PLAYER_NAME)

    companion object {
        private const val PLAYER_SCORE = 1
        private const val ADD_PLAYER = 2
        private const val TOTAL_SCORE = 3

        private const val ADD_PLAYER_NAME = "Add Player"

        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Player>() {
            override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem.playerNumber == newItem.playerNumber
            }

            override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem == newItem
            }
        }
    }

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerScoreTableViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ADD_PLAYER -> PlayerScoreTableViewHolder.AddPlayerViewHolder(
                inflater.inflate(R.layout.layout_add_player_viewholder, parent, false), listener)
            TOTAL_SCORE -> PlayerScoreTableViewHolder.TotalScoreViewHolder(
                inflater.inflate(R.layout.layout_total_viewholder, parent, false), round)
            else -> PlayerScoreTableViewHolder.PlayerViewHolder(
                inflater.inflate(R.layout.layout_player_viewholder, parent, false), round, scoreBoxListener)
        }
    }

    override fun onBindViewHolder(holder: PlayerScoreTableViewHolder, position: Int) {
        when (holder) {
            is PlayerScoreTableViewHolder.PlayerViewHolder -> holder.bind(getItem(position))
            is PlayerScoreTableViewHolder.AddPlayerViewHolder -> holder.bind()
            is PlayerScoreTableViewHolder.TotalScoreViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (round.roundNumber == Round.TOTALS_ROUND) {
            return TOTAL_SCORE
        } else if (getItem(position).name == ADD_PLAYER_NAME)
            return ADD_PLAYER

        return PLAYER_SCORE
    }

    fun updatePlayers(playerList: List<Player>) {
        val listToAdd = playerList.toMutableList()
        if (round.roundNumber != Round.TOTALS_ROUND)
            listToAdd.add(playerZero)

        submitList(listToAdd)
    }

    fun updateRound(newRound: Round) {
        round = newRound

        notifyDataSetChanged()
    }
}