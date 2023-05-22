package com.kazumaproject.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazumaproject.myapplication.database.DictionaryZeroDao
import com.kazumaproject.myapplication.database.model.DictionaryOne
import com.kazumaproject.myapplication.database.DictionaryOneDao
import com.kazumaproject.myapplication.database.model.DictionaryZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dictionaryZeroDao: DictionaryZeroDao,
    private val dictionaryOneDao: DictionaryOneDao
) : ViewModel(){

    private val _words = MutableStateFlow<MutableList<DictionaryZero>>(mutableListOf())
    val words = _words.asStateFlow()

    fun addWordToWords(dictionaryZero: DictionaryZero){
        _words.value.add(dictionaryZero)
    }

    fun updateWords(dictionaryZeros: MutableList<DictionaryZero>){
        _words.value = dictionaryZeros
    }

    fun insertDictionaryZeroWord(dictionaryZero: DictionaryZero) = viewModelScope.launch {
        dictionaryZeroDao.insertWordFromDictionary(dictionaryZero)
    }

    suspend fun getDictionaryZeroWordByYomi(dictionaryWordYomi: String) = dictionaryZeroDao.getDictionaryWordByYomi(dictionaryWordYomi)

    fun getAllDictionaryZeroWord(): Flow<List<DictionaryZero>>{

        return dictionaryZeroDao.getAllDictionaryWords()
    }

    fun insertDictionaryOneWord(dictionaryOne: DictionaryOne) = viewModelScope.launch {
        dictionaryOneDao.insertWordFromDictionary(dictionaryOne)
    }

    suspend fun getDictionaryOneWordByYomi(dictionaryWordYomi: String) = dictionaryOneDao.getDictionaryWordByYomi(dictionaryWordYomi)

    fun getAllDictionaryOneWord(): Flow<List<DictionaryOne>>{
        return dictionaryOneDao.getAllDictionaryWords()
    }


}