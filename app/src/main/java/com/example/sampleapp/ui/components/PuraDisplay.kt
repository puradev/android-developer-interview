package com.example.sampleapp.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sampleapp.R
import com.example.sampleapp.utils.ImageConstants

@Composable
fun PuraDisplay(context: Context) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Divider(color = Color(0, 200, 165), thickness = 4.dp)
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        AsyncImage(
            model = ImageConstants.puraLogo,
            contentDescription = context.getString(R.string.pura),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = context.getString(R.string.function).format(context.getString(R.string.noun)),
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
            text = context.getString(R.string.pura_description),
            style = TextStyle(
                color = Color(50, 50, 50),
                fontSize = 16.sp,
                fontWeight = FontWeight(600)
            )
        )

        Divider(color = Color(0, 200, 165), thickness = 8.dp)
    }
}