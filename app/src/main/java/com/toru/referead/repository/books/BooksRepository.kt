package com.toru.referead.repository.books

import android.util.Log
import com.toru.referead.model.books.BooksInfo
import com.toru.referead.model.books.BooksResponse
import com.toru.referead.model.books._BooksResponse
//import com.toru.referead.model.mapper.toBooksInfo
import com.toru.referead.remote.books.BooksNetworkPort
import com.toru.referead.remote.utils.Resource
import com.toru.referead.repository.utils.NetworkBoundResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    suspend fun getBooksInfoAsync(query: String): Flow<Resource<BooksInfo?>>
}

class BooksRepositoryImpl(
    private val api: BooksNetworkPort
) : BooksRepository {
    @ExperimentalCoroutinesApi
    override suspend fun getBooksInfoAsync(
        query: String
    ): Flow<Resource<BooksInfo?>> {
        return object : NetworkBoundResource<BooksInfo?, _BooksResponse?>() {
            override fun isError(response: _BooksResponse?): Throwable? {
                Log.d("isError", "response: $response")
                return /*if (/*response?.errorCode == GOOD_INFO_NOT_FOUND && */response?.errorMessage != null) {
                    Throwable(response?.errorMessage)
                } else */ null
            }

            override fun processResponse(response: _BooksResponse?): BooksInfo? = null//response?.toBooksInfo()

            override suspend fun fetchFromNetwork(): _BooksResponse? {
                //TODO("Not yet implemented")
                return null
            }

//            override suspend fun fetchFromNetwork(): BooksResponse? {
//                return api.getBooksInfoAsync(query, 1,2)
//            }
        }.asFlow()
    }
}