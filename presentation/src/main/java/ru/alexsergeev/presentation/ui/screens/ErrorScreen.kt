package ru.alexsergeev.presentation.ui.screens

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.alexsergeev.presentation.R
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.ui.components.TryElseButton
import ru.alexsergeev.presentation.utils.LockScreenOrientation

@Composable
internal fun ErrorScreen(onClick: () -> Unit) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = R.string.something_went_wrong),
            color = Color.Black,
            style = WinDiTheme.typography.heading2,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(id = R.string.will_try_again),
            color = Color.Black,
            style = WinDiTheme.typography.heading2,
            textAlign = TextAlign.Center,
        )
        TryElseButton(text = stringResource(id = R.string.try_else)) {
            onClick()
        }
    }
}