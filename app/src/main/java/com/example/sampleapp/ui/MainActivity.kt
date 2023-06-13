package com.example.sampleapp.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sampleapp.R
import com.example.sampleapp.ui.components.DisplayStateImage
import com.example.sampleapp.ui.components.DisplayWordData
import com.example.sampleapp.ui.components.PuraDisplay
import com.example.sampleapp.ui.theme.Dimens
import com.example.sampleapp.ui.theme.SampleAppTheme
import com.example.sampleapp.ui.view_model.MainViewModel
import com.example.sampleapp.ui.view_model.ScreenState
import com.example.sampleapp.utils.ImageConstants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppTheme {
                MainScreen(viewModel, this)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel, context: Context) {
    val wordInput = viewModel.wordFlow.collectAsState()
    val screenState = viewModel.screenState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = Dimens.elevationNormal
            ) {
                Text(
                    text = context.getString(R.string.dictionary),
                    style = SampleAppTheme.typography.h3,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(modifier = Modifier.fillMaxHeight()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.paddingLarge),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.paddingSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = wordInput.value,
                        onValueChange = { viewModel.setValue(it) },
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = { Text(context.getString(R.string.enter_word)) },
                        singleLine = true,
                        maxLines = 1,
                        modifier = Modifier
                            .weight(1f)
                            .height(58.dp)
                    )
                    Button(
                        onClick = { viewModel.getWordDefinition() },
                        modifier = Modifier
                            .height(intrinsicSize = IntrinsicSize.Max)
                            .padding(top = Dimens.paddingSmall)
                    ) {
                        Text(text = context.getString(R.string.search))
                    }
                }

                when (val state = screenState.value) {
                    ScreenState.InitialState -> DisplayStateImage(
                        url = ImageConstants.waitingInputUrl,
                        contentDescription = context.getString(R.string.waiting_input)
                    )

                    ScreenState.LoadingState -> DisplayStateImage(
                        url = ImageConstants.loadingUrl,
                        contentDescription = context.getString(R.string.loading)
                    )

                    ScreenState.SearchError -> DisplayStateImage(
                        url = ImageConstants.searchErrorUrl,
                        contentDescription = context.getString(R.string.search_error)
                    )

                    ScreenState.SomethingWentWrong -> DisplayStateImage(
                        url = ImageConstants.somethingWrongUrl,
                        contentDescription = context.getString(R.string.something_wrong)
                    )

                    is ScreenState.SuccessState -> {
                        DisplayWordData(wordData = state.data, context = context)
                    }

                    ScreenState.Thinking -> DisplayStateImage(
                        url = ImageConstants.thinkingUrl,
                        contentDescription = context.getString(R.string.thinking)
                    )

                    ScreenState.PuraState -> {
                        PuraDisplay(context = context)
                    }
                }
            }
        }
    }
}
