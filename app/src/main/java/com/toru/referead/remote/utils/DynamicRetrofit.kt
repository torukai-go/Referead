package com.toru.referead.remote.utils

import com.toru.referead.remote.books.BooksWebService
import kotlinx.serialization.json.Json
import okhttp3.CertificatePinner
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.Proxy
import java.util.concurrent.TimeUnit

class DynamicRetrofit {

    companion object {
        private const val READ_TIMEOUT = 30L
        private const val CONNECTION_TIMEOUT = 30L
        private val TIME_UNIT = TimeUnit.SECONDS
        private const val BASE_URL = "https://www.googleapis.com"
    }

    private var baseUrl: String = BASE_URL

    //@UnstableDefault
    private fun buildRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();
    }
    private var client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    //@UnstableDefault
    var apiBooks: BooksWebService = buildRetrofitClient().create(BooksWebService::class.java)
        private set

}