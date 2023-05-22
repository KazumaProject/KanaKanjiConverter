package com.kazumaproject.myapplication.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "dictionary_zero_table")
data class DictionaryZero(
    val yomi: String,
    val leftId: String,
    val rightId: String,
    val cost: String,
    val kanji: String,
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString()
)
