package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.alexsergeev.presentation.theme.WinDiTheme

@Composable
internal fun TextArea(
    hint: String,
    onTextChange: (String) -> Unit = {},
) {
    val text = rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .width(344.dp)
            .background(WinDiTheme.colors.disabledComponent, shape = RoundedCornerShape(18.dp))
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = text.value,
            onValueChange = {
                text.value = it
                onTextChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp)
                .background(WinDiTheme.colors.disabledComponent),
            textStyle = WinDiTheme.typography.subheading1,
            decorationBox = { innerTextField ->
                if (text.value.isEmpty()) {
                    Text(
                        text = hint,
                        color = WinDiTheme.colors.weakColor,
                        style = WinDiTheme.typography.body1
                    )
                }
                innerTextField()
            },
        )
    }
}