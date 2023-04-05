package com.toru.referead.remote.books
import com.toru.referead.model.books.BooksResponse
import com.toru.referead.model.books._BooksResponse
import com.toru.referead.remote.utils.DynamicRetrofit
//import kotlinx.serialization.UnstableDefault


class BooksNetworkAdapter(private val retrofit: DynamicRetrofit) : BooksNetworkPort {
    //@UnstableDefault
    override suspend fun getBooksInfoAsync(query: String, page: Int, perPage: Int): BooksResponse? {
        return retrofit.apiBooks.getBooksInfoAsync(query, page, perPage)
    }
}