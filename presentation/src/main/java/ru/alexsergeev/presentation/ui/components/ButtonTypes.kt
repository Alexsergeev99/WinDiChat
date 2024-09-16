package ru.alexsergeev.presentation.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.alexsergeev.presentation.theme.WinDiTheme

enum class Type {
    FIRST, SECOND, THIRD
}

@Composable
internal fun ButtonTypes(
    text: String = "Button",
    padding: Dp = 4.dp,
    width: Dp = 64.dp,
    onClick: () -> Unit = {},
    type: Type,
    enabled: Boolean = true,
    hovered: Boolean = false,
    focused: Boolean = false,
    modifier: Modifier = Modifier
        .width(width)
        .padding(vertical = padding)
        .graphicsLayer {
            alpha = if (!enabled) 0.5f else 1f
        }
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isHovered = interactionSource.collectIsHoveredAsState().value || hovered
    val isPressed = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val animateButton by animateFloatAsState(
        targetValue = if (isPressed.value || focused) 0.2f else 0f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )
    val backgroundColor =
        if (isHovered) WinDiTheme.colors.darkButtonColor else WinDiTheme.colors.middleButtonColor

    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(WinDiTheme.colors.lightButtonColor.copy(alpha = animateButton))
    ) {

        val onClickHandler: () -> Unit = {
            if (enabled) {
                if (focused) {
                    isPressed.value = true
                } else {
                    scope.launch {
                        isPressed.value = true
                        onClick()
                        delay(300)
                        isPressed.value = false
                    }
                }
            }
        }

        when (type) {
            Type.FIRST -> {
                Button(
                    onClick = onClickHandler,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = backgroundColor
                    ),
                    modifier = modifier,
                    enabled = enabled
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        style = WinDiTheme.typography.subheading2
                    )
                }
            }

            Type.SECOND -> {
                OutlinedButton(
                    onClick = onClickHandler,
                    border = BorderStroke(1.dp, backgroundColor),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    ),
                    modifier = modifier,
                    enabled = enabled
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        style = WinDiTheme.typography.subheading2
                    )
                }
            }

            Type.THIRD -> {
                OutlinedButton(
                    onClick = onClickHandler,
                    border = null,
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isPressed.value || focused) WinDiTheme.colors.lightButtonColor else Color.Transparent
                    ),
                    modifier = modifier,
                    enabled = enabled
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        style = WinDiTheme.typography.subheading2
                    )
                }
            }
        }
    }
}