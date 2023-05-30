package com.kazumaproject.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.google.common.base.Splitter
import com.kazumaproject.myapplication.database.model.DictionaryWord
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.trie4j.patricia.PatriciaTrie
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_view)
        val editText = findViewById<EditText>(R.id.edit_text)
        val progressBar = findViewById<ProgressBar>(R.id.loading_dictionary_progress)

        val pat = PatriciaTrie()

        lifecycleScope.launch {

            Log.d("create database: ","start: ${System.currentTimeMillis()}")

            viewModel.getAllWords().collectLatest {  words ->

                if (words.isEmpty()){
                    progressBar.isVisible = true
                    readDictionaryTxt("words.txt")
                    readDictionaryTxt("words_alpha.txt")
                }

                progressBar.isVisible = false
                words.forEach {
                    pat.insert(it.word)
                }
            }

            Log.d("create database: ","finished: ${System.currentTimeMillis()}")
            sharedPreference.isInitialStart = true

        }


        editText.addTextChangedListener { editable ->
            val resultList = mutableListOf<String?>()
            if(editable.isNullOrBlank()) {
                textView.text = ""
                resultList.clear()
                return@addTextChangedListener
            }
            editable.let { ed ->
                pat.predictiveSearch(ed.toString()).forEachIndexed { index, s ->


                    when(ed.length){
                        0,1,2,3 ->{
                            if (ed.length == s.length ||
                                ed.length + 1 == s.length ||
                                ed.length + 2 == s.length){
                                resultList.add(s)
                            }
                        }
                        else ->{
                            resultList.add(s)
                        }
                    }



                }
                textView.text = resultList.toString()
            }
        }

    }


    private suspend fun readDictionaryTxt(
        fileName: String,
    ) {
        try {
            val inputStream = resources.assets.open(fileName)
            inputStream.bufferedReader().useLines { lines ->

                viewModel.insertWords(lines.map {
                    DictionaryWord(
                        word = it,
                        createdTime = System.currentTimeMillis(),
                    )
                }.asIterable())

            }

        } catch (e: Exception) {
            // エラー処理
        }
    }


}