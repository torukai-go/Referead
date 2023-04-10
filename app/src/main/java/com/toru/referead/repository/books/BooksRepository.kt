package com.toru.referead.repository.books

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.toru.referead.remote.books.BooksWebService
import com.toru.referead.remote.books.BooksPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksRepository @Inject constructor (private val api: BooksWebService) {
    fun getSearchResult(query: String, filter: String? = null, ) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 60,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { BooksPagingSource(api, query, filter ) }
        ).liveData
}