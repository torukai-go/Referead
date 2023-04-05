package com.toru.referead.model.books

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class BooksInfo(
@field:Json(name = "kind")
val kind: String,
@field:Json(name = "id")
val id: String,
@field:Json(name = "etag")
val etag: String,
@field:Json(name = "selfLink")
val selfLink: String,
@field:Json(name = "volumeInfo")
val volumeInfo: VolumeInfo
):Parcelable

@Parcelize
data class VolumeInfo(
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "authors")
    val authors: List<String>,
    @field:Json(name = "publisher")
    val publisher: String,
    @field:Json(name = "publishedDate")
    val publishedDate: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "pageCount")
    val pageCount: Int,
    @field:Json(name = "imageLinks")
    val imageLinks: ImageLinks
):Parcelable

@Parcelize
data class ImageLinks(
    @field:Json(name = "smallThumbnail")
    val smallThumbnail: String,
    @field:Json(name = "thumbnail")
    val thumbnail: String
):Parcelable
