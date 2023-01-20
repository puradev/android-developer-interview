package com.example.sampleapp.repository

/**
 * Created by Robert Duriancik on 17/01/2023.
 */
interface WordsRepository {
    /**
     * This method tries to find a definition of the provided word.
     *
     * @return WordData in case the word has been found in the dictionary
     * @throws WordNotFoundException in case the word has not been found in the dictionary
     * @throws HttpRequestException in case the request fails
     */
    suspend fun getDefinitions(word: String): WordData
}
