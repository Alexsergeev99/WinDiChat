package ru.alexsergeev.presentation.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.R
import ru.alexsergeev.presentation.viewmodel.InputPhoneNumberViewModel

private const val ERROR_NUMBER = "Неправильный номер"

@Composable
internal fun InputPhoneNumberButtonChanger(
    navController: NavController,
    checkPhoneNumberLength: MutableState<Boolean>,
    inputPhoneNumberViewModel: InputPhoneNumberViewModel = koinViewModel()
) {

    val user by inputPhoneNumberViewModel.getUserData().collectAsStateWithLifecycle()
    val status by inputPhoneNumberViewModel.getSendCodeStatus().collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val focusManager = LocalFocusManager.current

    val onButtonClick = {
        if (checkPhoneNumberLength.value) {
            focusManager.clearFocus()
            scope.launch {
                inputPhoneNumberViewModel.sendCodeAndUpdateStatus("${user.phone.countryCode}${user.phone.basicNumber}")
            }
        } else {
            Toast.makeText(context, ERROR_NUMBER, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(status) {
        if (status) {
            navController.navigate("registration_screen")
        }
    }

    when (checkPhoneNumberLength.value) {
        true -> SimpleButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = stringResource(id = R.string.resume),
            onClick = {
                onButtonClick()
            }
        )

        else -> DisabledButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = stringResource(id = R.string.resume),
            onClick = {
                Toast.makeText(context, ERROR_NUMBER, Toast.LENGTH_LONG).show()
            }
        )
    }

}