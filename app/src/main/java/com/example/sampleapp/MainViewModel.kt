package com.example.sampleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.api.WordsServiceImpl
import com.example.sampleapp.repository.WordData
import com.example.sampleapp.repository.WordNotFoundException
import com.example.sampleapp.repository.WordsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val wordsRepository by lazy {
        WordsRepositoryImpl(WordsServiceImpl())
    }

    private val _mainState = MutableStateFlow<MainState>(MainState.Idle)
    val mainState = _mainState.asStateFlow()

    private val _word = MutableStateFlow("")
    val word = _word.asStateFlow()

    fun onWordChange(it: String) {
        _word.value = it
    }

    fun onClickSearch() {
        if (word.value.isBlank()) {
            _mainState.value = MainState.Error(Error("No word provided"))
        } else {
            searchWord()
        }
    }

    private fun searchWord() {
        viewModelScope.launch {
            _mainState.value = MainState.Loading

            runCatching {
                wordsRepository.getDefinitions(word.value)
            }.onSuccess {
                _mainState.value = MainState.Success(it)
            }.onFailure {
                if(it is NoSuchElementException) {
                    _mainState.value = MainState.Error(WordNotFoundException(word.value))
                } else {
                    _mainState.value = MainState.Error(it)
                }
            }
        }
    }
}

sealed class MainState {
    object Idle : MainState()

    object Loading : MainState()

    data class Success(
        val data: WordData
    ) : MainState()

    data class Error(
        val exception: Throwable
    ) : MainState()
}