package com.elve8valley.astutetest.data.repositories

import com.elve8valley.astutetest.base.BaseRepository
import com.elve8valley.astutetest.data.networks.MyApi
import javax.inject.Inject

class ImageRepository @Inject constructor(private val api: MyApi) : BaseRepository() {

    suspend fun getAllImages()=apiRequest {
        api.getAllImages()
    }
}