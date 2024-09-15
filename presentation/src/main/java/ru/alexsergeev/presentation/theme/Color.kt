package ru.alexsergeev.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class Colors(
    val weakColor: Color,
    val activeComponent: Color,
    val disabledComponent: Color,
    val switchBackground: Color,
    val disabledText: Color,
)

val ColorInstance = Colors(
    weakColor = Color(0xFF76778E),
    activeComponent = Color(0xFF9A10F0),
    disabledComponent = Color(0xFFF6F6FA),
    switchBackground = Color(0xFFEFEFEF),
    disabledText = Color(0xFF9797AF),
)

val LocalColors = staticCompositionLocalOf { ColorInstance }