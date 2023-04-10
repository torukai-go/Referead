package com.toru.referead.ui.main

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.toru.referead.model.books.QueryParams
import com.toru.referead.repository.books.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@HiltViewModel
@FlowPreview
class MainViewModel @Inject constructor(
    private val repository: BooksRepository
) : ViewModel()
{
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)
    private val currentFilter = MutableLiveData<String?>()

    fun searchBooks(query: String,  inTitle: String? = null, inAuthor: String? = null, subject: String? = null, filter: String? = null){
        var q = query

        if(!inTitle.isNullOrEmpty()) q += "+intitle:$inTitle"
        if(!inAuthor.isNullOrEmpty()) q += "+inauthor:$inAuthor"
        if(!subject.isNullOrEmpty()) q += "+subject:$subject"

        currentQuery.value = q
        currentFilter.value = filter
    }

    val books = CombinedMediatorLiveData(currentQuery, currentFilter).switchMap { mediatorState ->
        val queryString = mediatorState.queryString ?: ""
        val filterString = mediatorState.filterString

        repository.getSearchResult(query = queryString, filter = filterString).cachedIn(viewModelScope)
    }

    inner class CombinedMediatorLiveData(
        query: LiveData<String?>,
        filter: LiveData<String?>
    ) : MediatorLiveData<QueryParams<String?, String?>>() {
        init {
            addSource(query) { updatedQuery -> value = QueryParams(updatedQuery, filter.value) }
            addSource(filter) { updatedFilter -> value = QueryParams(query.value, updatedFilter) }
        }
    }

    companion object{
        private const val DEFAULT_QUERY = "kotlin"
    }
}
