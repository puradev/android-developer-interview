package com.example.sampleapp.repository

/**
 * Created by Robert Duriancik on 17/01/2023.
 */
class WordNotFoundException(word: String): Exception("The word '$word' has not been found in the dictionary")
