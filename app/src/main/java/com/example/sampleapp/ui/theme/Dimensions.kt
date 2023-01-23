package com.example.sampleapp.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Robert Duriancik on 23/01/2023.
 */
class Dimensions(
    val paddingSmall: Dp,
    val paddingNormal: Dp,
    val paddingLarge: Dp,
    val elevationSmall: Dp,
    val elevationNormal: Dp,
    val elevationLarge: Dp
)

val DefaultDimens = Dimensions(
    paddingSmall = 8.dp,
    paddingNormal = 16.dp,
    paddingLarge = 24.dp,
    elevationSmall = 4.dp,
    elevationNormal = 6.dp,
    elevationLarge = 8.dp
)
