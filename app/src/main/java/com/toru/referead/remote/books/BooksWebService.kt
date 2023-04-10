package com.toru.referead.remote.books

import com.toru.referead.model.books.BooksResponse
import retrofit2.http.*

interface BooksWebService {
    @GET("/books/v1/volumes?")
    suspend fun getBooksInfoAsync(@Query("q") searchQuery: String,
                                  @Query("filter") filter : String? = null,
                                  @Query("startIndex") page: Int,
                                  @Query("maxResults") perPage: Int): BooksResponse
}