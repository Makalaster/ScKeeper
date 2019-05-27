package com.makalaster.sckeeper.gameactivity.adapters

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.makalaster.sckeeper.R
import com.makalaster.data.models.Player
import com.makalaster.data.models.Round
import com.makalaster.widgets.ScoreBoxListener
import kotlinx.android.synthetic.main.layout_player_viewholder.view.*
import kotlinx.android.synthetic.main.layout_player_viewholder.view.player_name
import kotlinx.android.synthetic.main.layout_total_viewholder.view.*

abstract class PlayerScoreTableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class PlayerViewHolder(itemView: View, private val round: Round, private val scoreBoxListener: ScoreBoxListener): PlayerScoreTableViewHolder(itemView) {
        fun bind(player: Player) {
            itemView.player_name.text = player.name
            itemView.score_container.setItemId(player.playerNumber)

            round.scores[player.playerNumber]?.let {
                itemView.score_container.setScore(it)
            }

            itemView.score_container.setListener(scoreBoxListener)
        }
    }

    class TotalScoreViewHolder(itemView: View, private val round: Round): PlayerScoreTableViewHolder(itemView) {
        fun bind(player: Player) {
            itemView.player_name.text = player.name

            round.scores[player.playerNumber]?.let {
                itemView.player_score.text = it.toString()
            }
        }
    }

    class AddPlayerViewHolder(itemView: View, private val listener: OnAddPlayerClickedListener): PlayerScoreTableViewHolder(itemView) {
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