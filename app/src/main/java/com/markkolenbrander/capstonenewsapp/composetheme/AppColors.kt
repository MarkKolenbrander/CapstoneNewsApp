package com.markkolenbrander.capstonenewsapp.composetheme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class AppColors(
    primary: Color,
    secondary: Color,
    textPrimary: Color,
    textSecondary: Color,
    background: Color,
    error: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var background by mutableStateOf(background)
        private set
    var error by mutableStateOf(error)
        private set
    var isLight by mutableStateOf(isLight)
        internal set
    fun copy(
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary,
        background: Color= this.background,
        error: Color = this.error,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        primary,
        secondary,
        textPrimary,
        textSecondary,
        background,
        error,
        isLight
    )

    fun updateColorsFrom(other: AppColors) {
        primary = other.primary
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        background = other.background
        error = other.error
    }

}

fun lightColors(
    primary: Color = colorLightPrimary,
    secondary: Color = colorLightSecondary,
    textPrimary: Color = colorLightTextPrimary,
    textSecondary: Color = colorLightTextSecondary,
    background: Color = colorLightBackground,
    error: Color = colorLightError
): AppColors = AppColors(
    primary = primary,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    isLight = true
)

fun darkColors(
    primary: Color = colorDarkPrimary,
    secondary: Color = colorDarkSecondary,
    textPrimary: Color = colorDarkTextPrimary,
    textSecondary: Color = colorDarkTextSecondary,
    background: Color = colorDarkBackground,
    error: Color = colorDarkError
): AppColors = AppColors(
    primary = primary,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    isLight = false
)

private val colorLightPrimary = Color(0xFF213058)
private val colorLightSecondary = Color(0xFF28696A)
private val colorLightTextPrimary = Color(0xFFF0E6D7)
private val colorLightTextSecondary = Color(0xFFF4AE3F)
private val colorLightBackground = Color(0xFF213058)
private val colorLightError = Color(0xFFD62222)

private val colorDarkPrimary = Color(0xFFFFB400)
private val colorDarkSecondary = Color(0xFFFFB400)
private val colorDarkTextPrimary = Color(0xFF000000)
private val colorDarkTextSecondary = Color(0xFF6C727A)
private val colorDarkBackground = Color(0xFFFFFFFF)
private val colorDarkError = Color(0xFFD62222)

internal val LocalColors = staticCompositionLocalOf{ lightColors() }

internal val LocalDarkColors = staticCompositionLocalOf{ darkColors() }