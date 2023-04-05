package com.toru.referead.repository.books

import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.toru.referead.remote.books.CurrenciesService
import com.toru.referead.remote.books.ExamplePagingSource
import com.toru.referead.remote.books.NetworkServiceRetrofitCoroutinesMoshiAdapter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksRepository2 @Inject constructor (private val api: CurrenciesService) {

    fun getSearchResult(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 60,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { ExamplePagingSource(api, query) }
        ).liveData
}