package com.kazumaproject.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kazumaproject.myapplication.database.model.DictionaryZero
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryZeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordFromDictionary(dictionaryZero: DictionaryZero)

    @Query("DELETE FROM dictionary_zero_table")
    suspend fun deleteAllDictionaryWords()


    @Query("SELECT * FROM dictionary_zero_table WHERE yomi = :dictionaryWordYomi")
    suspend fun getDictionaryWordByYomi(dictionaryWordYomi: String): DictionaryZero?

    @Query("SELECT * FROM dictionary_zero_table ORDER BY yomi DESC")
    fun getAllDictionaryWords(): Flow<List<DictionaryZero>>
}