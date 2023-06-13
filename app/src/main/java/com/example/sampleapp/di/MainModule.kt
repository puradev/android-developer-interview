package com.example.sampleapp.di

import com.example.sampleapp.api.WordsService
import com.example.sampleapp.api.WordsServiceImpl
import com.example.sampleapp.repository.WordsRepository
import com.example.sampleapp.repository.WordsRepositoryImpl
import com.example.sampleapp.ui.view_model.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        MainViewModel(get())
    }

    single<WordsService> { WordsServiceImpl() }

    factory<WordsRepository> { WordsRepositoryImpl(get()) }
}
