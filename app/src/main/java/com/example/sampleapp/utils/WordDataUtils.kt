package com.example.sampleapp.utils

import android.content.Context
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.example.sampleapp.R
import com.example.sampleapp.repository.WordData

fun WordData.getDefinitions(context: Context): String {
    val definitions = this.definitions.reduce { acc, value ->
        context.getString(R.string.definitions_accumulator).format(
            acc,
            value.capitalize(Locale.current)
        )
    }

    return context.getString(R.string.definitions_end)
        .format(definitions.capitalize(Locale.current))
}