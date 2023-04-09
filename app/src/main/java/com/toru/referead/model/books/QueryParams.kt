package com.toru.referead.model.books

data class QueryParams<T1, T2, T3, T4, T5>(val queryString: T1, val titleString: T2, val authorString: T3, val subjectString: T4, val filterString: T5)
