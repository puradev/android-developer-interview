package com.example.sampleapp

import android.app.Application
import com.example.sampleapp.di.mainModule
import org.koin.core.context.startKoin

class WordsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(mainModule)
        }
    }
}
