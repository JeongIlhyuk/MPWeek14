package com.example.week14.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.week14.viewmodel.ItemEntity

@Database(
    entities = [ItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun getItemDao(): ItemDAO

    companion object {
        @Volatile
        private var DBInstance: ItemDatabase? = null
        fun getDBInstance(context: Context): ItemDatabase {
            return DBInstance ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "ItemDB"
                ).build()
                DBInstance = instance
                instance
            }
        }
    }
}