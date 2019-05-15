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
    private var decrementButton: Button
    private var incrementButton: Button
    private var scoreDisplay: TextView
    private var enterScore: EditText

    private lateinit var scoreBoxListener: ScoreBoxListener

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_score_box, this)

        decrementButton = view.decrement_score
        decrementButton.setOnClickListener{ scoreBoxListener.onDecrementTap() }
        decrementButton.setOnLongClickListener{ scoreBoxListener.onDecrementLongPress() }

        incrementButton = view.increment_score
        incrementButton.setOnClickListener { scoreBoxListener.onIncrementTap() }
        incrementButton.setOnLongClickListener { scoreBoxListener.onIncrementLongPress() }

        scoreDisplay = view.score_display
        scoreDisplay.setOnClickListener { scoreBoxListener.onScoreDisplayTap() }
        scoreDisplay.setOnLongClickListener { scoreBoxListener.onScoreDisplayLongPress() }

        enterScore = view.enter_score
    }

    fun setListener(listener: ScoreBoxListener) {
        scoreBoxListener = listener
    }
}

interface ScoreBoxListener {
    fun onDecrementTap()
    fun onDecrementLongPress(): Boolean

    fun onIncrementTap()
    fun onIncrementLongPress(): Boolean

    fun onScoreDisplayTap()
    fun onScoreDisplayLongPress(): Boolean
}