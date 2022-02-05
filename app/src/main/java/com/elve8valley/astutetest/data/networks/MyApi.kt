package com.elve8valley.astutetest.data.networks

import com.elve8valley.astutetest.data.responses.Images
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface MyApi {
    @Headers("Content-Type: application/json")
    @GET("get_all_photos")
    suspend fun getAllImages(): Images
}