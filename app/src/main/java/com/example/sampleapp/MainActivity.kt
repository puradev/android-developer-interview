package com.example.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sampleapp.repository.WordData
import com.example.sampleapp.repository.WordNotFoundException
import com.example.sampleapp.ui.theme.Dimens
import com.example.sampleapp.ui.theme.SampleAppTheme
import java.io.IOException

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {

    val mainScreenState = viewModel.mainState.collectAsState()
    val wordInputState = viewModel.word.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = Dimens.elevationNormal
            ) {
                Text(
                    text = "Dictionary",
                    style = SampleAppTheme.typography.h3,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.paddingLarge)
                    .align(Alignment.TopStart),
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimens.paddingSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = wordInputState.value,
                        enabled = mainScreenState.value !is MainState.Loading,
                        onValueChange = { viewModel.onWordChange(it) },
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = { Text("Enter word") },
                        singleLine = true,
                        maxLines = 1,
                        modifier = Modifier
                            .weight(1f)
                            .height(58.dp)
                    )
                    Button(
                        onClick = { viewModel.onClickSearch() },
                        modifier = Modifier
                            .height(intrinsicSize = IntrinsicSize.Max)
                            .padding(top = Dimens.paddingSmall)
                    ) {
                        Text(text = "Search")
                    }
                }
                when (val state = mainScreenState.value) {
                    is MainState.Idle -> {}
                    is MainState.Loading -> {
                        LoadingState()
                    }

                    is MainState.Error -> {
                        ErrorState(state.exception)
                    }

                    is MainState.Success -> {
                        SuccessState(state.data)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleAppTheme {
        MainScreen()
    }
}

@Composable
private fun LoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(throwable: Throwable) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (throwable) {
            is IOException -> {
                Text(
                    "Looks like your internet is not working.\nPlease check your connection and try again.",
                    textAlign = TextAlign.Center,
                )
            }
            is WordNotFoundException -> {
                Text(
                    "We haven't found this word in our database. ",
                    textAlign = TextAlign.Center
                )
            }
            else -> {
                Text(
                    "Opss... Something went wrong.\nPlease try again.",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun SuccessState(wordData: WordData) {
    Column {
        Text(
            wordData.word,
            style = SampleAppTheme.typography.h2,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            wordData.function,
            style = SampleAppTheme.typography.subtitle1,
            fontStyle = FontStyle.Italic
        )

        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            itemsIndexed(wordData.definitions) { index, definition ->
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "${index + 1}. $definition"
                )
            }
        }
    }
}