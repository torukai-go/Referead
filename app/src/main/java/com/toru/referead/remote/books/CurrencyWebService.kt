package com.toru.referead.remote.books

import com.toru.referead.model.books.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesService {

    @GET("/books/v1/volumes")
    suspend fun getCurrencies(@Query("q") searchQuery: String,
                              @Query("startIndex") page: Int,
                              @Query("maxResults") perPage: Int): BooksResponse
}