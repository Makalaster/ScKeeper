package com.makalaster.sckeeper.gameactivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makalaster.data.models.Player
import com.makalaster.sckeeper.R
import com.makalaster.widgets.ScoreBoxListener
import java.util.ArrayList

class PlayerRecyclerAdapter(private val scoreBoxListener: ScoreBoxListener,
                            private val listener: PlayerScoreTableViewHolder.AddPlayerViewHolder.OnAddPlayerClickedListener):
    RecyclerView.Adapter<PlayerScoreTableViewHolder>() {

    private var playerList = ArrayList<Player>()
    private val playerZero = Player("Add Player", -1)

    companion object {
        private const val PLAYER_SCORE = 1
        private const val ADD_PLAYER = 2
    }

    init {
        playerList.add(playerZero)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerScoreTableViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            2 -> PlayerScoreTableViewHolder.AddPlayerViewHolder(
                inflater.inflate(R.layout.layout_add_player_viewholder, parent, false), listener)
            else -> PlayerScoreTableViewHolder.PlayerViewHolder(
                inflater.inflate(R.layout.layout_player_viewholder, parent, false), scoreBoxListener)
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

    fun setList(newPlayerList: List<Player>) {
        if (newPlayerList.isNotEmpty())
            playerList = ArrayList(newPlayerList)
        else {
            playerList.clear()
            playerList.add(playerZero)
        }
        notifyDataSetChanged()
    }
}