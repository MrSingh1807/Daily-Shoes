package com.example.dailyshoes.ui.modules

import android.content.Context
import androidx.room.Room
import com.example.dailyshoes.ui.dao.LocalDBDao
import com.example.dailyshoes.ui.di.DB_NAME
import com.example.dailyshoes.ui.di.ShoeDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext mContext: Context) = Room.databaseBuilder(
        mContext, ShoeDB::class.java, DB_NAME
    ).build()


    @Provides
    @Singleton
    fun provideShoeDao(db: ShoeDB): LocalDBDao = db.localDB()
}