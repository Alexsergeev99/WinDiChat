package ru.alexsergeev.presentation.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.models.UserUiModel
import ru.alexsergeev.presentation.viewmodel.RegistrationScreenViewModel
import ru.alexsergeev.presentation.viewmodel.UserProfileViewModel

private const val TOAST_NO_NAME_TEXT = "К нам без имени нельзя:("

@Composable
internal fun EditProfileButtonChanger(
    navController: NavController,
    registrationScreenViewModel: RegistrationScreenViewModel = koinViewModel(),
) {

    val ctx = LocalContext.current
    val focusManager = LocalFocusManager.current
    val user by registrationScreenViewModel.getUserDataWithoutApi().collectAsStateWithLifecycle()

    if (user.name.firstName.isNotEmpty()) {
        SimpleButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = "Сохранить",
            padding = 30.dp,
            onClick = {
                registrationScreenViewModel.registerUser(user)
                focusManager.clearFocus()
                navController.navigate("code_screen")
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