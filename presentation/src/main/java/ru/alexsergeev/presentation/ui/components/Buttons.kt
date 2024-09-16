package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.alexsergeev.presentation.theme.WinDiTheme

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

@Composable
internal fun LogoButton(image: Int) {
    OutlinedButton(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 4.dp)
            .width(72.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = WinDiTheme.colors.middleButtonColor,
            containerColor = Color.White
        ),
        border = BorderStroke(1.5.dp, WinDiTheme.colors.middleButtonColor),
        onClick = { /*TODO*/ }) {
        LogoItem(image)
    }
}

@Composable
internal fun LogoItem(image: Int) {
    Icon(
        painter = painterResource(id = image),
        contentDescription = "icon_logo",
        tint = WinDiTheme.colors.middleButtonColor
    )
}