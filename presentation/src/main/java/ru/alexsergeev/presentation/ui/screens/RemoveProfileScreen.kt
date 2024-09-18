package ru.alexsergeev.presentation.ui.screens

import android.content.pm.ActivityInfo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.alexsergeev.presentation.R
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.ui.components.ButtonsInRemoveProfileScreen
import ru.alexsergeev.presentation.utils.LockScreenOrientation

@Composable
internal fun RemoveProfileScreen(
    navController: NavController,
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = "Точно удалить профиль?", style = WinDiTheme.typography.heading1)
                }
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigateUp()
                        },
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "close"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
                text = "Не надо...... Мы крутые",
                style = WinDiTheme.typography.subheading1,
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Мы удалим профиль, но не сразу. У вас будет 30 дней, чтобы зайти и обратно всё вернуть",
                style = WinDiTheme.typography.subheading1,
                maxLines = 3,
            )
        }
        ButtonsInRemoveProfileScreen()
    }
}