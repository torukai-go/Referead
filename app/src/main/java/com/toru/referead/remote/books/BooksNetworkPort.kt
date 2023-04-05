package com.toru.referead.remote.books

import com.toru.referead.model.books.BooksResponse

interface BooksNetworkPort {
    suspend fun getBooksInfoAsync(query: String, page: Int, perPage: Int): BooksResponse?
}
