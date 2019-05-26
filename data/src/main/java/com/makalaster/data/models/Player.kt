package com.makalaster.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.makalaster.data.models.Player.Companion.TABLE_NAME
import kotlin.collections.ArrayList

@Entity(tableName = TABLE_NAME)
data class Player(@PrimaryKey(autoGenerate = true)
                  @ColumnInfo(name = "player_number")
                  var playerNumber: Int,
                  @ColumnInfo(name = "player_name")
                  var name: String = "") {

    companion object {
        const val TABLE_NAME = "PLAYERS"
    }

    override fun equals(other: Any?): Boolean {
        return (other as Player).playerNumber == this.playerNumber
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}