package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import ru.alexsergeev.presentation.theme.WinDiTheme

@Composable
internal fun TryElseButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 4.dp)
            .clip(RoundedCornerShape(6.dp))
            .width(186.dp)
            .height(36.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White,
            containerColor = WinDiTheme.colors.activeComponent
        ),
        shape = RectangleShape,
        onClick = { onClick() }) {
        Text(text = text, color = Color.White, style = WinDiTheme.typography.subheading1)
    }
}