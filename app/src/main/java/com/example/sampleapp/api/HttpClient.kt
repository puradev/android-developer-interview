package com.example.sampleapp.api

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Robert Duriancik on 16/01/2023.
 */
object HttpClient {

}

interface Test {
    @GET
    fun test(): Response<Unit>
}
