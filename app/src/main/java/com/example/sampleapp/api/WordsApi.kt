package com.example.sampleapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Robert Duriancik on 17/01/2023.
 */
interface WordsApi {
    @GET("{word}")
    suspend fun getDefinitions(
        @Path("word") word: String,
        @Query("key") apiKey: String
    ): Response<GetDefinitionResponseEntity>
}
