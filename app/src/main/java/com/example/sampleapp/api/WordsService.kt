package com.example.sampleapp.api

import retrofit2.Response

/**
 * Created by Robert Duriancik on 17/01/2023.
 */
interface WordsService {
    suspend fun getDefinitions(word: String): Response<GetDefinitionResponseEntity>
}


