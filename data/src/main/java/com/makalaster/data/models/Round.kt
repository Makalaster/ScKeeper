package com.makalaster.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.makalaster.data.models.Round.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Round(@PrimaryKey @ColumnInfo(name = "round_number") val roundNumber: Int) {
    // Round 0 displays player totals
    companion object {
        const val TABLE_NAME = "ROUNDS"
    }
}