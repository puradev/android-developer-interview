package com.example.sampleapp.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.repository.HttpRequestException
import com.example.sampleapp.repository.WordsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val wordsRepository: WordsRepository
) : ViewModel() {
    private val _wordFlow = MutableStateFlow("")
    val wordFlow = _wordFlow.asStateFlow()

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.InitialState)
    val screenState = _screenState.asStateFlow()

    fun setValue(string: String) {
        _screenState.tryEmit(ScreenState.Thinking)
        _wordFlow.tryEmit(string)
    }

    fun getWordDefinition() {
        _screenState.tryEmit(ScreenState.LoadingState)
        _wordFlow.value.let {
            viewModelScope.launch {
                try {
                    if (it == "pura") {
                        _screenState.tryEmit(ScreenState.PuraState)
                    } else {
                        val wordData = wordsRepository.getDefinitions(it)
                        _screenState.tryEmit(ScreenState.SuccessState(wordData))
                    }

                } catch (ex: HttpRequestException) {
                    _screenState.tryEmit(ScreenState.SearchError)
                } catch (ex: Exception) {
                    _screenState.tryEmit(ScreenState.SomethingWentWrong)
                }

            }
        }
    }
}
