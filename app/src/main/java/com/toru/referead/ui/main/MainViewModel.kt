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
    private val currentInTitle = MutableLiveData<String?>()
    private val currentInAuthor = MutableLiveData<String?>()
    private val currentSubject = MutableLiveData<String?>()

    fun searchBooks(query: String,  inTitle: String? = null, inAuthor: String? = null, subject: String? = null, filter: String? = null){
        currentQuery.value = query
        currentInTitle.value = inTitle
        currentInAuthor.value = inAuthor
        currentSubject.value = subject
        currentFilter.value = filter
    }

    val books = CombinedMediatorLiveData(currentQuery,  currentInTitle, currentInAuthor, currentSubject, currentFilter).switchMap { mediatorState ->
        val queryString = mediatorState.queryString ?: ""
        val titleString = mediatorState.titleString
        val authorString = mediatorState.authorString
        val subjectString = mediatorState.subjectString
        val filterString = mediatorState.filterString

        repository.getSearchResult(query = queryString, inTitle = titleString, inAuthor = authorString, subject = subjectString, filter = filterString).cachedIn(viewModelScope)
    }

    inner class CombinedMediatorLiveData(
        query: LiveData<String?>,
        title: LiveData<String?>,
        author: LiveData<String?>,
        subject: LiveData<String?>,
        filter: LiveData<String?>
    ) : MediatorLiveData<QueryParams<String?, String?, String?, String?, String?>>() {
        init {
            addSource(query) { updatedQuery -> value = QueryParams(updatedQuery, title.value, author.value, subject.value, filter.value) }
            addSource(title) { updatedTitle -> value = QueryParams(query.value, updatedTitle, author.value, subject.value, filter.value) }
            addSource(author) { updatedAuthors -> value = QueryParams(query.value, title.value, updatedAuthors, subject.value, filter.value) }
            addSource(subject) { updatedSubject -> value = QueryParams(query.value, title.value, author.value, updatedSubject, filter.value) }
            addSource(filter) { updatedFilter -> value =QueryParams(query.value, title.value, author.value, subject.value, updatedFilter) }
        }
    }

    companion object{
        private const val DEFAULT_QUERY = "kotlin"
    }
}
