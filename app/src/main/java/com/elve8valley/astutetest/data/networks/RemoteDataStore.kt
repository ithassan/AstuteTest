package com.elve8valley.astutetest.data.networks

import android.content.Context
import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataStore @Inject constructor() {
    companion object{
        private const val BASE_URL="http://blogswizards.com/mobile_app_assignment/api/"
    }

    fun <Api> buildApi(api:Class<Api>, context: Context? = null, authToken:String? = null):Api
    {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor{ chain ->
                chain.proceed(chain.request().newBuilder().also {
                  //  it.addHeader("Authorization","Bearer ${authToken}")
                }.build())
            }.also { client ->

                    val logging = HttpLoggingInterceptor()
                    logging.level = HttpLoggingInterceptor.Level.BODY
                    client.addInterceptor(logging)


            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)

    }
}