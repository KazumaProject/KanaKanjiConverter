package com.kazumaproject.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kazumaproject.myapplication.database.model.DictionaryOne
import com.kazumaproject.myapplication.database.model.DictionaryZero

@Database(
    entities = [DictionaryZero::class, DictionaryOne::class],
    version = 7,
    exportSchema = false
)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract fun dictionaryZeroDao(): DictionaryZeroDao
    abstract fun dictionaryOneDao(): DictionaryOneDao
}