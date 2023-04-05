package com.toru.referead.usecase

import com.toru.referead.model.books.BooksInfo

import com.toru.referead.remote.utils.Resource
import com.toru.referead.repository.books.BooksRepository
import kotlinx.coroutines.flow.Flow

class RequestBooksInfoUseCase(private val repository: BooksRepository) {
    suspend operator fun invoke(query: String): Flow<Resource<BooksInfo?>> {
        return repository.getBooksInfoAsync(query)
    }
}
