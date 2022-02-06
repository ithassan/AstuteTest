package com.elve8valley.astutetest.data.networks

import com.elve8valley.astutetest.data.responses.Data
import com.elve8valley.astutetest.data.responses.Images
import com.elve8valley.astutetest.data.responses.SingleImage
import retrofit2.http.*

interface MyApi {
    @Headers("Content-Type: application/json")
    @GET("get_all_photos")
    suspend fun getAllImages(): Images

    @Headers("Content-Type: application/json")
    @GET("get_single_photo_detail")
    suspend fun getImageById(@Query("photo_id") id : String): SingleImage

    @Headers("Content-Type: application/json")
    @GET("get_suggested_photos")
    suspend fun getSuggestedImages(@Query("photo_id") id : String): Images
}