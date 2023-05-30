package com.kazumaproject.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazumaproject.myapplication.database.DictionaryWordDao
import com.kazumaproject.myapplication.database.model.DictionaryWord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dictionaryWordDao: DictionaryWordDao
) : ViewModel(){

    suspend fun insertWord(word: DictionaryWord){
        dictionaryWordDao.insertWordFromDictionary(
            word
        )
    }

    suspend fun insertWords(words: Iterable<DictionaryWord>){
        dictionaryWordDao.insertWordsFromDictionary(
            words
        )
    }

    fun getAllWords(): Flow<List<DictionaryWord>>{
        return dictionaryWordDao.getAllDictionaryWords()
    }

}