package com.kazumaproject.myapplication.di

import android.content.Context
import androidx.room.Room
import com.kazumaproject.myapplication.SharedPreference
import com.kazumaproject.myapplication.database.DictionaryDatabase
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
    ) = Room.databaseBuilder(context, DictionaryDatabase::class.java, "dictionary_word.db")
        .build()

    @Singleton
    @Provides
    fun providesDictionaryZeroDao(db: DictionaryDatabase) = db.dictionaryZeroDao()

    @Singleton
    @Provides
    fun providesDictionaryOneDao(db: DictionaryDatabase) = db.dictionaryOneDao()

    @Singleton
    @Provides
    fun providesSharedPreference(
        @ApplicationContext context: Context
    ): SharedPreference{
        SharedPreference.init(context)
        return SharedPreference
    }

}