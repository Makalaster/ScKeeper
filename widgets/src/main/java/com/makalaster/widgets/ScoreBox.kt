package com.makalaster.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_score_box.view.*

class ScoreBox(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val decrementButton: Button
    private val incrementButton: Button
    private val scoreDisplay: TextView
    private val enterScore: EditText

    private var score: Int = 0
    private var itemId: Int = 0

    private lateinit var scoreBoxListener: ScoreBoxListener

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_score_box, this)

        decrementButton = view.decrement_score
        decrementButton.setOnClickListener{
            decrementScore(1)
            scoreBoxListener.onDecrementTap(itemId)
        }
        decrementButton.setOnLongClickListener{
            decrementScore(5)
            scoreBoxListener.onDecrementLongPress(itemId)
        }

        incrementButton = view.increment_score
        incrementButton.setOnClickListener {
            incrementScore(1)
            scoreBoxListener.onIncrementTap(itemId)
        }
        incrementButton.setOnLongClickListener {
            incrementScore(5)
            scoreBoxListener.onIncrementLongPress(itemId)
        }

        scoreDisplay = view.score_display
        scoreDisplay.setOnClickListener {
            scoreBoxListener.onScoreDisplayTap(itemId)
        }
        scoreDisplay.setOnLongClickListener {
            scoreBoxListener.onScoreDisplayLongPress(itemId)
        }

        enterScore = view.enter_score
    }

    fun setListener(listener: ScoreBoxListener) {
        scoreBoxListener = listener
    }

    fun setScore(score: Int) {
        this.score = score
        scoreDisplay.text = score.toString()
    }

    fun setItemId(id: Int) {
        itemId = id
    }

    private fun incrementScore(addScore: Int) {
        score += addScore
        scoreDisplay.text = score.toString()
    }

    private fun decrementScore(removeScore: Int) {
        score -= removeScore
        scoreDisplay.text = score.toString()
    }
}

interface ScoreBoxListener {
    fun onDecrementTap(id: Int)
    fun onDecrementLongPress(id: Int): Boolean

    fun onIncrementTap(id: Int)
    fun onIncrementLongPress(id: Int): Boolean

    fun onScoreDisplayTap(id: Int)
    fun onScoreDisplayLongPress(id: Int): Boolean
}