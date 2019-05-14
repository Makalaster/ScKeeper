package com.makalaster.sckeeper.gameactivity.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.makalaster.sckeeper.R
import com.makalaster.sckeeper.models.Player
import java.util.ArrayList

class PlayerRecyclerAdapter : RecyclerView.Adapter<PlayerScoreTableViewHolder>(),
    PlayerScoreTableViewHolder.AddPlayerViewHolder.OnAddPlayerClickedListener {

    private val playerList = ArrayList<Player>()

    companion object {
        private const val PLAYER_SCORE = 1
        private const val ADD_PLAYER = 2
    }

    init {
        playerList.add(Player("Add Player", 0, ArrayList()))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerScoreTableViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            2 -> PlayerScoreTableViewHolder.AddPlayerViewHolder(
                inflater.inflate(R.layout.layout_add_player_viewholder, parent, false), this)
            else -> PlayerScoreTableViewHolder.PlayerViewHolder(
                inflater.inflate(R.layout.layout_player_viewholder, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerScoreTableViewHolder, position: Int) {
        when (holder) {
            is PlayerScoreTableViewHolder.PlayerViewHolder -> holder.bind(playerList[position])
            is PlayerScoreTableViewHolder.AddPlayerViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == playerList.size - 1)
            return ADD_PLAYER

        return PLAYER_SCORE
    }

    override fun onAddPlayerClicked() {
        playerList.add(playerList.size - 1, Player("Player ${playerList.size - 1}", playerList.size - 1, ArrayList()))
        notifyItemInserted(playerList.size - 1)
    }
}