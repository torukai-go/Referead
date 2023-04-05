package com.toru.referead.remote.books

import com.toru.referead.model.books.BooksResponse
import retrofit2.http.*

interface BooksWebService {
    @GET("/books/v1/volumes?filter=free-ebooks&")
    suspend fun getBooksInfoAsync(@Query("q") searchQuery: String,
                                  @Query("startIndex") page: Int,
                                  @Query("maxResults") perPage: Int): BooksResponse?
}