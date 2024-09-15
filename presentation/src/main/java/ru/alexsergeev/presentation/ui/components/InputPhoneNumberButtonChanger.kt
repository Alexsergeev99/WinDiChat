package ru.alexsergeev.presentation.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.alexsergeev.presentation.R

private const val ERROR_NUMBER = "Неправильный номер"

@Composable
internal fun InputPhoneNumberButtonChanger(
    navController: NavController,
    checkPhoneNumberLength: MutableState<Boolean>
) {

    val ctx = LocalContext.current

    val focusManager = LocalFocusManager.current

    when (checkPhoneNumberLength.value) {
        true -> SimpleButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = stringResource(id = R.string.resume),
            onClick = {
                focusManager.clearFocus()
                navController.navigate("code_screen")
            }
        )

        else -> DisabledButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = stringResource(id = R.string.resume),
            onClick = {
                Toast.makeText(ctx, ERROR_NUMBER, Toast.LENGTH_LONG).show()
            }
        )
    }
}