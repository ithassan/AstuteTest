package com.elve8valley.astutetest.di

import android.content.Context
import com.elve8valley.astutetest.data.networks.MyApi
import com.elve8valley.astutetest.data.networks.RemoteDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideApi( remoteDataStore: RemoteDataStore):MyApi{
        return remoteDataStore.buildApi(MyApi::class.java)
    }


}