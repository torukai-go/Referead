package com.toru.referead.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.toru.referead.usecase.RequestBooksInfoUseCase
import androidx.lifecycle.*
import com.toru.referead.model.books.BooksResponse
import com.toru.referead.remote.books.NetworkServiceRetrofitCoroutinesMoshiAdapter
import com.toru.referead.repository.books.BooksRepository2
import com.toru.referead.ui.base.IModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@FlowPreview
class MainViewModel @Inject constructor(
    private val repository: BooksRepository2
) : ViewModel()
{

//    private val _state = MutableLiveData<MainState>().apply { value = MainState.InitialState }
//    private val retrofitCoroutinesMoshiConverter = NetworkServiceRetrofitCoroutinesMoshiAdapter()
    //private var _authInfo: AuthInfo? = null
//    override val intents: Channel<MainIntent> = Channel(Channel.UNLIMITED)
//    override val state: LiveData<MainState>
       // get() = _state

    private lateinit var _books: MutableLiveData<BooksResponse>

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val books = currentQuery.switchMap {queryString ->
        repository.getSearchResult(queryString).cachedIn(viewModelScope)
    }

    fun searchBooks(query: String){
        currentQuery.value = query
    }


//    val flow = Pager(
//        // Configure how data is loaded by passing additional properties to
//        // PagingConfig, such as prefetchDistance.
//        PagingConfig(pageSize = 20)
//    ) {
//        ExamplePagingSource(backend, query)
//    }.flow
//        .cachedIn(viewModelScope)

    init {
        //handlerIntent()
    }

//    @FlowPreview
//    private fun handlerIntent() {
//        viewModelScope.launch {
//            intents.consumeAsFlow().collect {
//                when (it) {
////                    is MainIntent.InitialIntent -> updateState { MainState.InitialState }
////                    is MainIntent.TimeoutIntent -> updateState { MainState.IdleState }
//                    // TODO:
//                    //is MainIntent.RequestBooksIntent -> requestBooksInfo(it.searchQuery)
//                    else -> {}
//                }
//            }
//        }
//    }


//    private suspend fun updateState(handler: suspend (intent: MainState) -> MainState) {
//        Log.d("MainViewModel updateState", "${state.value}")
//        viewModelScope.launch(Dispatchers.Main) {
//            _state.value = handler(state.value!!)
//            //XLog.d(getLog("MainViewModel updateState", "${_state.value}"))
//        }
//    }

//    fun requestBooksInfo(searchQuery: String?) {
//        Log.d("requestNotificationStatus", "searchQuery: $searchQuery")
//
//        viewModelScope.launch(Dispatchers.IO) {
//            val booksData = retrofitCoroutinesMoshiConverter.request(searchQuery ?: "")
//            //binding.textviewResponseData.text = currencyData.toString()
//            Log.d("Data", booksData.toString())
//            _books.value = booksData
//        }
//
////        viewModelScope.launch(Dispatchers.IO) {
////            try {
////                val request = BooksRequest(searchQuery ?: "")
////                //val network = _settingsInfo?.network
////                requestBooksInfoUseCase(request).collect { resource ->
////                    when (resource.status) {
////                        Resource.Status.SUCCESS -> {
////
////                            val booksInfo: BooksInfo? = resource.data
////                            Log.d("Status", "Success")
////
////                            //MainState.IdleState
////                        }
////                        Resource.Status.LOADING -> {
////                            //MainState.LoadingState(network) updateState
////                        }
////                        Resource.Status.ERROR -> {
////                            Log.d("Status", "Error")
////                            //updateState { MainState.ContentNotFound(network) }
////                        }
////                    }
////                }
////            } catch (e: Exception) {
////                Log.d("Exception", "Error")
////
////                //updateState { MainState.ErrorState(e.message, _settingsInfo?.network) }
////            }
////        }
//    }

    companion object{
        private const val DEFAULT_QUERY = "java"
    }


}
