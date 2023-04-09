package com.toru.referead.di

import com.toru.referead.remote.books.BooksWebService
import com.toru.referead.repository.books.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MainViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideRepository(api: BooksWebService) = BooksRepository(api)
}