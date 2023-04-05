package com.toru.referead.di

import com.toru.referead.remote.books.BooksNetworkAdapter
import com.toru.referead.remote.books.BooksNetworkPort
import com.toru.referead.remote.utils.DynamicRetrofit
import com.toru.referead.repository.books.BooksRepository
import com.toru.referead.repository.books.BooksRepositoryImpl
import com.toru.referead.usecase.RequestBooksInfoUseCase
import android.content.ContentResolver
import com.toru.referead.remote.books.CurrenciesService
import com.toru.referead.remote.books.NetworkServiceRetrofitCoroutinesMoshiAdapter
import com.toru.referead.repository.books.BooksRepository2
import com.toru.referead.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import okhttp3.internal.cacheGet
//import kotlinx.serialization.UnstableDefault
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object ModuleProvider {
    enum class Scopes {
        MAIN_SCREEN
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    //@UnstableDefault
    private val appModules = module {
        single<ContentResolver> { androidContext().contentResolver }
        single { DynamicRetrofit() }
        single { BooksRepository2(api = get()) }
        //single<CurrenciesService>{ }

        viewModel {
            MainViewModel(repository = get())
        }
//        scope(named(Scopes.MAIN_SCREEN.name)) {
//            scoped<BooksNetworkPort> { BooksNetworkAdapter(get()) as BooksNetworkPort }
//
//            //scoped<CurrenciesService> { }
//            //scoped<BooksRepository2> { CurrenciesService as BooksRepository2 }
//            //scoped { RequestBooksInfoUseCase(get()) }
//
//
//        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    //@UnstableDefault
    val modules: List<Module>
        get() {
            return ArrayList<Module>().apply {
                add(appModules)
            }
        }
}
