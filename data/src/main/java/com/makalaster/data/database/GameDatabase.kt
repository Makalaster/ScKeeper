package com.makalaster.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.makalaster.data.models.Player
import com.makalaster.data.models.Round

@Database(version = 1, entities = [Player::class, Round::class])
@TypeConverters(com.makalaster.data.database.TypeConverters::class)
abstract class GameDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun roundDao(): RoundDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getInstance(context: Context): GameDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    GameDatabase::class.java, "Game_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class TypeConverters {
    @TypeConverter
    fun fromList(list: List<Int>): String = Gson().toJson(list)

    @TypeConverter
    fun toList(scores: String): ArrayList<Int> = Gson().fromJson(scores, object: TypeToken<ArrayList<Int>>() {}.type)
}