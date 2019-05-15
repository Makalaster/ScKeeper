package com.makalaster.sckeeper.gameactivity.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.makalaster.sckeeper.R
import com.makalaster.sckeeper.models.Player
import com.makalaster.widgets.ScoreBoxListener
import kotlinx.android.synthetic.main.layout_player_viewholder.view.*

abstract class PlayerScoreTableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class PlayerViewHolder(itemView: View, private val scoreBoxListener: ScoreBoxListener) : PlayerScoreTableViewHolder(itemView) {
        fun bind(player: Player) {
            itemView.player_name.text = player.name
            itemView.score_container.setListener(scoreBoxListener)
        }
    }

    class AddPlayerViewHolder(itemView: View, val listener: OnAddPlayerClickedListener) : PlayerScoreTableViewHolder(itemView) {
        fun bind() {
            itemView.findViewById<Button>(R.id.add_player).setOnClickListener {
                listener.onAddPlayerClicked()
            }
        }

        interface OnAddPlayerClickedListener {
            fun onAddPlayerClicked()
        }
    }
}