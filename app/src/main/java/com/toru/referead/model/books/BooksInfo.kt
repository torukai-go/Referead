package com.toru.referead.model.books

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class BooksInfo(
@field:Json(name = "kind")
val kind: String?,
@field:Json(name = "id")
val id: String,
@field:Json(name = "etag")
val etag: String,
@field:Json(name = "selfLink")
val selfLink: String,
@field:Json(name = "volumeInfo")
val volumeInfo: VolumeInfo,
@field:Json(name = "saleInfo")
val saleInfo: SaleInfo,
@field:Json(name = "accessInfo")
val accessInfo: AccessInfo
):Parcelable {

    @Parcelize
    data class VolumeInfo(
        @field:Json(name = "title")
        val title: String,
        @field:Json(name = "subtitle")
        val subtitle: String?,
        @field:Json(name = "authors")
        val authors: List<String>?,
        @field:Json(name = "publisher")
        val publisher: String?,
        @field:Json(name = "publishedDate")
        val publishedDate: String?,
        @field:Json(name = "description")
        val description: String?,
        @field:Json(name = "pageCount")
        val pageCount: Int?,
        @field:Json(name = "imageLinks")
        val imageLinks: ImageLinks?,
        @field:Json(name = "canonicalVolumeLink")
        val canonicalVolumeLink: String?,
        @field:Json(name = "previewLink")
        val previewLink: String?
    ):Parcelable

    @Parcelize
    data class ImageLinks(
        @field:Json(name = "smallThumbnail")
        val smallThumbnail: String?,
        @field:Json(name = "thumbnail")
        val thumbnail: String?
    ):Parcelable

    @Parcelize
    data class SaleInfo (
        @field:Json(name = "country")
        val country: String,
        @field:Json(name = "buyLink")
        val buyLink: String?
    ):Parcelable

    @Parcelize
    data class AccessInfo (
        @field:Json(name = "country")
        val country: String,
    ):Parcelable
}




