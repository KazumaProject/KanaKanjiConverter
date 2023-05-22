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
import com.kazumaproject.myapplication.database.model.DictionaryOne
import com.kazumaproject.myapplication.database.model.DictionaryZero
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
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
            progressBar.isVisible = true
            Log.d("create database: ","start: ${System.currentTimeMillis()}")
            val readTextThenCreateDatabaseAsyncList = listOf(
                async {
                    readDictionary00("dictionary00.txt", pat)
                },
                async {
                    readDictionary00("dictionary01.txt", pat)
                },
                async {
                    readDictionary00("dictionary02.txt", pat)
                },
                async {
                    readDictionary00("dictionary03.txt", pat)
                },
                async {
                    readDictionary00("dictionary04.txt", pat)
                },
                async {
                    readDictionary00("dictionary05.txt", pat)
                },
                async {
                    readDictionary00("dictionary06.txt", pat)
                },
                async {
                    readDictionary00("dictionary07.txt", pat)
                },
                async {
                    readDictionary00("dictionary08.txt", pat)
                },
                async {
                    readDictionary00("dictionary09.txt", pat)
                },
                async {
                    readDictionary00("suffix.txt", pat)
                },
            )

            readTextThenCreateDatabaseAsyncList.awaitAll()

            progressBar.isVisible = false
            Log.d("create database: ","finished: ${System.currentTimeMillis()}")
            sharedPreference.isInitialStart = true

        }


        editText.addTextChangedListener { editable ->
            val resultList = mutableListOf<String?>()
            val resultList2 = emptyList<String>()
            if(editable.isNullOrBlank()) {
                textView.text = ""
                resultList.clear()
            }
            editable?.let { ed ->
                pat.commonPrefixSearch(ed.toString()).forEach {
                    resultList.add(it)
                }
                textView.text = resultList.toString()
            }
        }

    }


    private suspend fun readDictionary00(
        fileName: String,
        patriciaTrie: PatriciaTrie
    ) = withContext(Dispatchers.Default) {
        try {
            val inputStream = resources.assets.open(fileName)
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach { line ->
                    val a = Splitter.on("\t").split(
                        line
                    )
                    patriciaTrie.insert(a.first())
                    Log.d("Loaded dictionary word: ", "${a.first()} ${a.last()}")
                }
            }

        } catch (e: Exception) {
            // エラー処理
        }
    }


}