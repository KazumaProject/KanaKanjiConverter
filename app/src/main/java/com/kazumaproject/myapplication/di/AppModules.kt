package com.kazumaproject.myapplication.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kazumaproject.myapplication.SharedPreference
import com.kazumaproject.myapplication.database.DictionaryDatabase
import com.kazumaproject.myapplication.database.DictionaryWordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Singleton
    @Provides
    fun providesNoteDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, DictionaryDatabase::class.java, "english_word.db").build()

    @Singleton
    @Provides
    fun providesDictionaryWordDao(db: DictionaryDatabase): DictionaryWordDao = db.dictionaryWordDao()

    @Singleton
    @Provides
    fun providesSharedPreference(
        @ApplicationContext context: Context
    ): SharedPreference{
        SharedPreference.init(context)
        return SharedPreference
    }

}