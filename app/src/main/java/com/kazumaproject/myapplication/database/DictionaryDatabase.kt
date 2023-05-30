package com.kazumaproject.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kazumaproject.myapplication.database.model.DictionaryWord

@Database(
    entities = [DictionaryWord::class],
    version = 6,
    exportSchema = false
)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract fun dictionaryWordDao(): DictionaryWordDao
}