package com.kazumaproject.myapplication.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "dictionary_word_table")
data class DictionaryWord(
    val word: String,
    val createdTime: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
