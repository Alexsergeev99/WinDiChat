package ru.alexsergeev.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SimpleButton(
    modifier: Modifier = Modifier,
    text: String,
    padding: Dp = 4.dp,
    width: Dp = 96.dp,
    onClick: () -> Unit = {}
) {
    ButtonTypes(
        modifier = modifier,
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.FIRST,
    )
}

@Composable
fun SimpleOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    padding: Dp = 4.dp,
    width: Dp = 96.dp,
    onClick: () -> Unit = {}
) {
    ButtonTypes(
        modifier = modifier,
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.SECOND,
    )
}

@Composable
fun SimpleTextButton(
    modifier: Modifier = Modifier,
    text: String,
    padding: Dp = 4.dp,
    width: Dp = 96.dp,
    onClick: () -> Unit = {}
) {
    ButtonTypes(
        modifier = modifier,
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.THIRD,
    )
}

@Composable
fun HoveredButton(text: String, padding: Dp = 4.dp, width: Dp = 96.dp, onClick: () -> Unit = {}) {
    ButtonTypes(
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.FIRST,
        hovered = true,
    )
}

@Composable
fun HoveredOutlinedButton(
    text: String,
    padding: Dp = 4.dp,
    width: Dp = 96.dp,
    onClick: () -> Unit = {}
) {
    ButtonTypes(
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.SECOND,
        hovered = true,
    )
}

@Composable
fun HoveredTextButton(
    text: String,
    padding: Dp = 4.dp,
    width: Dp = 96.dp,
    onClick: () -> Unit = {}
) {
    ButtonTypes(
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.THIRD,
        hovered = true,
    )
}

@Composable
fun FocusedButton(text: String, padding: Dp = 4.dp, width: Dp = 96.dp, onClick: () -> Unit = {}) {
    ButtonTypes(
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.FIRST,
        focused = true,
    )
}

@Composable
fun FocusedOutlinedButton(
    text: String,
    padding: Dp = 4.dp,
    width: Dp = 96.dp,
    onClick: () -> Unit = {}
) {
    ButtonTypes(
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.SECOND,
        focused = true,
    )
}

@Composable
fun FocusedTextButton(
    text: String,
    padding: Dp = 4.dp,
    width: Dp = 96.dp,
    onClick: () -> Unit = {}
) {
    ButtonTypes(
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.THIRD,
        focused = true,
    )
}

@Composable
fun DisabledButton(
    modifier: Modifier = Modifier,
    text: String,
    padding: Dp = 4.dp,
    width: Dp = 96.dp,
    onClick: () -> Unit = {},
) {
    ButtonTypes(
        modifier = modifier.graphicsLayer {
            alpha = 0.5f
        },
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.FIRST,
    )
}

@Composable
fun DisabledOutlinedButton(
    text: String,
    padding: Dp = 4.dp,
    width: Dp = 96.dp,
    onClick: () -> Unit = {}
) {
    ButtonTypes(
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.SECOND,
    )
}

@Composable
fun DisabledTextButton(
    modifier: Modifier = Modifier,
    text: String,
    padding: Dp = 4.dp,
    width: Dp = 96.dp,
    onClick: () -> Unit = {}
) {
    ButtonTypes(
        modifier = modifier.graphicsLayer {
            alpha = 0.5f
        },
        text = text,
        padding = padding,
        width = width,
        onClick = onClick,
        type = Type.THIRD,
    )
}