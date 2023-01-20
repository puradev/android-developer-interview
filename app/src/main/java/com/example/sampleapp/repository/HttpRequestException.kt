package com.example.sampleapp.repository

/**
 * Created by Robert Duriancik on 20/01/2023.
 */
class HttpRequestException(
    val code: Int,
    val rawMessage: String?
) : Exception() {
    override fun toString(): String {
        return "HttpRequestException: CODE=$code, MESSAGE=$rawMessage"
    }
}
