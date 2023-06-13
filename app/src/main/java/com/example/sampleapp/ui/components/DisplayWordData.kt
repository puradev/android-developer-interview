package com.example.sampleapp.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sampleapp.R
import com.example.sampleapp.repository.WordData
import com.example.sampleapp.utils.getDefinitions

@Composable
fun DisplayWordData(wordData: WordData, context: Context) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Divider(color = Color(100, 150, 200), thickness = 4.dp)

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            text = wordData.word.capitalize(Locale.current), style = TextStyle(
                color = Color(100, 150, 200),
                fontSize = 48.sp,
                fontWeight = FontWeight(700)
            )
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = context.getString(R.string.function).format(wordData.function),
            style = TextStyle(
                color = Color(50, 50, 50),
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight(400)
            )
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 36.dp),
            text = wordData.getDefinitions(context),
            style = TextStyle(
                color = Color(50, 50, 50),
                fontSize = 16.sp,
                fontWeight = FontWeight(600)
            )
        )

        Divider(color = Color(100, 150, 200), thickness = 8.dp)
    }
}