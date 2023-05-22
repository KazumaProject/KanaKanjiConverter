package com.kazumaproject.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kazumaproject.myapplication.database.model.DictionaryOne
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryOneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordFromDictionary(dictionaryZero: DictionaryOne)

    @Query("DELETE FROM dictionary_one_table")
    suspend fun deleteAllDictionaryWords()


    @Query("SELECT * FROM dictionary_one_table WHERE yomi = :dictionaryWordYomi")
    suspend fun getDictionaryWordByYomi(dictionaryWordYomi: String): DictionaryOne?

    @Query("SELECT * FROM dictionary_one_table ORDER BY yomi DESC")
    fun getAllDictionaryWords(): Flow<List<DictionaryOne>>
}