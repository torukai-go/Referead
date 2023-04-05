package com.toru.referead.model.books

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class BooksResponse(
    @field:Json(name = "kind")
    val kind: String,
    @field:Json(name = "totalItems")
    val totalItems: Int,
    @field:Json(name = "items")
    val items: List<BooksInfo>
):Parcelable
