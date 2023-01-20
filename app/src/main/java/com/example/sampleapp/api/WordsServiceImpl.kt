package com.example.sampleapp.api

import com.example.sampleapp.BuildConfig
import retrofit2.Response

/**
 * Created by Robert Duriancik on 17/01/2023.
 */
internal class WordsServiceImpl : WordsService {
    private val api = HttpClient.client.create(WordsApi::class.java)

    override suspend fun getDefinitions(word: String): Response<List<GetDefinitionResponseEntity>> {
        return api.getDefinitions(word, BuildConfig.DICTIONARY_API_KEY)
    }
}
