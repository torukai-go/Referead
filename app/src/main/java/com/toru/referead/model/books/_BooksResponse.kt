package com.toru.referead.model.books

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class _BooksResponse(
    @SerialName("title") val title: String? = null, //
    @SerialName("subtitle") val subtitle: String? = null, //
    @SerialName("authors") val authors: String? = null, //
    @SerialName("publisher") val publisher: String? = null, //
    @SerialName("publishedDate") val publishedDate: String? = null, //
    @SerialName("description") val description: String? = null, //
    @SerialName("pageCount") val pageCount: Int? = 0, //
    @SerialName("thumbnail") val thumbnail: String? = null, //
    @SerialName("previewLink") val previewLink: String? = null, //
    @SerialName("infoLink") val infoLink: String? = null, //
    @SerialName("buyLink") val buyLink: String? = null, //
)