package com.elve8valley.astutetest.data.repositories

import com.elve8valley.astutetest.base.BaseRepository
import com.elve8valley.astutetest.data.networks.MyApi
import javax.inject.Inject

class ImageRepository @Inject constructor(private val api: MyApi) : BaseRepository() {

    suspend fun getAllImages()=apiRequest {
        api.getAllImages()
    }

    suspend fun getImageById(id:String)=apiRequest {
        api.getImageById(id)
    }

    suspend fun getSuggestedImages(id:String)=apiRequest {
        api.getSuggestedImages(id)
    }
}