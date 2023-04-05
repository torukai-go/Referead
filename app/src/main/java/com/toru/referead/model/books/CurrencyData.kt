package com.toru.referead.model.books

import com.squareup.moshi.Json

data class CurrencyData(
    @field:Json(name = "data")
    val data: List<Currency>
)
