package com.toru.referead.di

import com.toru.referead.remote.books.CurrenciesService
import com.toru.referead.repository.books.BooksRepository2
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
    fun provideRepository(api: CurrenciesService) = BooksRepository2(api)

}