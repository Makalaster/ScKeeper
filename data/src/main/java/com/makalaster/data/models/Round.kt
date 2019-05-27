package com.makalaster.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.makalaster.data.models.Round.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Round(@PrimaryKey
                 @ColumnInfo(name = "round_number")
                 val roundNumber: Int) {

    @ColumnInfo(name = "player_scores")
    var scores: HashMap<Int, Int> = HashMap()

    companion object {
        const val TABLE_NAME = "ROUNDS"

        const val TOTALS_ROUND = -1
    }
}