package com.example.unsplash_app.api

import com.example.unsplash_app.utils.feat.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

//    private val loggingInterceptor = HttpLoggingInterceptor().apply {
//        level = if ()
//    }

    private val publicOkHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Client-ID ${Constants.MY_ACCESS_KEY}")
                    .build()
                    chain.proceed(request)
            }
            .readTimeout(Constants.READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }


    private val publicRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(publicOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> publicApi(service: Class<T>): T= publicRetrofit.create(service)

}