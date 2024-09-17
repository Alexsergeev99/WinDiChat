package ru.alexsergeev.presentation.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.alexsergeev.presentation.models.UserUiModel

private const val TOAST_NO_NAME_TEXT = "К нам без имени нельзя:("

@Composable
internal fun EditProfileButtonChanger(navController: NavController, user: UserUiModel) {

    val ctx = LocalContext.current
    val focusManager = LocalFocusManager.current

    if (user.name.firstName.isNotEmpty()) {
        SimpleButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = "Сохранить",
            padding = 30.dp,
            onClick = {
                focusManager.clearFocus()
                navController.navigate("main_screen")
            }
        )
    } else {
        DisabledButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = "Сохранить",
            padding = 30.dp,
            onClick = {
                Toast.makeText(ctx, TOAST_NO_NAME_TEXT, Toast.LENGTH_LONG)
                    .show()
            }
        )
    }
}