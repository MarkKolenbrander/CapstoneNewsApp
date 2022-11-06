package com.markkolenbrander.capstonenewsapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColorScheme(
    primary = Color.White,
    secondary = YellowCapstone,
    onPrimary = BlueCapstone,
    onSecondary = GreenCapstone,
    background = GreyCapstone,
    onBackground = GreenCapstone,
    surface = GreyCapstone,
    onSurface = YellowCapstone,
    error = RedErrorDark,
    onError = RedErrorLight,


)

private val DarkThemeColors = darkColorScheme(
    primary = BlueCapstone,
    secondary = GreenCapstone,
    onPrimary = WhiteCapstone,
    onSecondary = YellowCapstone,
    background = BlueCapstone,
    onBackground = GreenCapstone,
    surface = BlueCapstone,
    onSurface = YellowCapstone,
    error = RedErrorDark,
    onError = RedErrorLight,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme =  if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = typography,
        shapes = shapes,
    ){
        content()
    }
}