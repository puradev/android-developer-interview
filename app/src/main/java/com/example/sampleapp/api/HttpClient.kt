package com.example.sampleapp.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Robert Duriancik on 16/01/2023.
 */
internal object HttpClient {
    private const val BASE_URL = "https://www.dictionaryapi.com/api/v3/references/collegiate/json/"

    val client: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}
