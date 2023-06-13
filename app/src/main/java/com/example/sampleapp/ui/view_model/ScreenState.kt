package com.example.sampleapp.ui.view_model

import com.example.sampleapp.repository.WordData

sealed class ScreenState() {
    object InitialState : ScreenState()
    object LoadingState : ScreenState()
    object Thinking : ScreenState()
    data class SuccessState(val data: WordData) : ScreenState()
    object SearchError : ScreenState()
    object SomethingWentWrong : ScreenState()
    object PuraState : ScreenState()
}