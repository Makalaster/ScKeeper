package com.makalaster.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.makalaster.data.models.Player.Companion.TABLE_NAME
import kotlin.collections.ArrayList

@Entity(tableName = TABLE_NAME)
data class Player(@ColumnInfo(name = "player_name") var name: String = "",
                  @PrimaryKey @ColumnInfo(name = "player_number") val playerNumber: Int = 0) {

    @ColumnInfo(name = "player_scores")
    var scores: ArrayList<Int> = ArrayList() // score 0 contains total score

    companion object {
        const val TABLE_NAME = "PLAYERS"
    }
}