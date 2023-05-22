package com.kazumaproject.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SharedPreference {
    private lateinit var preferences: SharedPreferences

    private val SYNTAX = Pair("is_initial_start",false)
    fun init(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var isInitialStart: Boolean?
        get() = preferences.getBoolean(SYNTAX.first, SYNTAX.second)

        set(value) = preferences.edit {
            it.putBoolean(SYNTAX.first, value ?: true)
        }
}