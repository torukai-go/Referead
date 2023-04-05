package com.toru.referead.remote.books

import com.toru.referead.model.books.BooksResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.toru.referead.remote.utils.DynamicRetrofit

class NetworkServiceRetrofitCoroutinesMoshiAdapter {

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

    private val service = retrofit.create(CurrenciesService::class.java)

    suspend fun request(q: String, page: Int, perPage: Int) = service.getCurrencies(q, page, perPage)

//    interface CurrenciesService {
//
//        companion object{
//
//        }
//
//        @GET("/books/v1/volumes")
//        suspend fun getCurrencies(@Query("q") searchQuery: String,
//                                  @Query("startIndex") page: Int,
//                                  @Query("maxResults") perPage: Int): BooksResponse
//    }

}