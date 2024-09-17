package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.alexsergeev.presentation.R
import ru.alexsergeev.presentation.theme.WinDiTheme

@Composable
internal fun Search(
    hint: String,
    hintColor: Color = WinDiTheme.colors.weakColor,
    isSearch: Boolean = true,
    padding: Dp = 8.dp,
    isEnabled: (Boolean) = true,
    height: Dp = 56.dp,
    width: Dp = 344.dp,
    cornerShape: Shape = RoundedCornerShape(18.dp),
    onClickInMainScreenSearch: () -> Unit = {},
    onSearchClicked: () -> Unit = {},
    onTextChange: (String) -> Unit = {},
    text: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue("")) }
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier
            .padding(vertical = padding)
            .height(height)
            .width(width)
            .background(WinDiTheme.colors.disabledComponent, shape = cornerShape)
            .clickable { onSearchClicked() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(WinDiTheme.colors.disabledComponent, shape = CircleShape)
                .clickable {
                    if (text.value.text.isNotEmpty()) {
                        text.value = TextFieldValue("")
                        onTextChange("")
                    }
                },
        ) {
            if (isSearch) {
                Icon(
                    modifier = Modifier
                        .size(16.dp),
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    tint = WinDiTheme.colors.weakColor
                )
            }
        }
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .background(WinDiTheme.colors.disabledComponent),
            value = text.value,
            onValueChange = {
                text.value = it
                onTextChange(it.text)
                onClickInMainScreenSearch()
            },
            enabled = isEnabled,
            textStyle = WinDiTheme.typography.subheading1,
            decorationBox = { innerTextField ->
                if (text.value.text.isEmpty()) {
                    Text(
                        text = hint,
                        color = hintColor,
                        style = WinDiTheme.typography.body1
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }, onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            singleLine = true,
        )
    }
}