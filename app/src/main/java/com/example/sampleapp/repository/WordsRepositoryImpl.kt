package com.example.sampleapp.repository

import android.util.Log
import com.example.sampleapp.api.WordsService
import com.example.sampleapp.utils.LOG_TAG

/**
 * Created by Robert Duriancik on 17/01/2023.
 */
class WordsRepositoryImpl(private val wordsService: WordsService) : WordsRepository {

    override suspend fun getDefinitions(word: String): WordData {
        val response = wordsService.getDefinitions(word)
        if (response.isSuccessful) {
            try {
                val body = response.body()
                if (body != null) {
                    val firstFoundWord = body.first()
                    Log.d(LOG_TAG, "Definition for word $word found $firstFoundWord")
                    return with(firstFoundWord) { WordData(word, function, definitions) }
                } else {
                    Log.w(LOG_TAG, "Definition for word $word not found")
                    throw WordNotFoundException(word)
                }
            } catch (ex: Exception) {
                Log.e(LOG_TAG, "Error while parsing request body for word $word", ex)
                throw WordNotFoundException(word)
            }
        } else {
            Log.w(LOG_TAG, "Get definitions request not successful")
            throw HttpRequestException(response.code(), response.errorBody()?.string())
        }
    }
}
