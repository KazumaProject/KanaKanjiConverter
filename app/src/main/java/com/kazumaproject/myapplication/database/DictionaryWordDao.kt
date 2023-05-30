package com.kazumaproject.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kazumaproject.myapplication.database.model.DictionaryWord
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryWordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordFromDictionary(word: DictionaryWord)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordsFromDictionary(words: Iterable<DictionaryWord>)

    @Query("DELETE FROM dictionary_word_table")
    suspend fun deleteAllDictionaryWords()

    @Query("SELECT * FROM dictionary_word_table ORDER BY id DESC")
    fun getAllDictionaryWords(): Flow<List<DictionaryWord>>
}