package com.example.sampleapp.api

import com.squareup.moshi.Json

/**
 * Created by Robert Duriancik on 17/01/2023.
 */
data class GetDefinitionResponseEntity(
    @field:Json(name = "fl") val function: String,
    @field:Json(name = "shortdef") val definitions: List<String>
)
