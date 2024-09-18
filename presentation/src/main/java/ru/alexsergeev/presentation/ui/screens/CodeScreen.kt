package ru.alexsergeev.presentation.ui.screens

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.R
import ru.alexsergeev.presentation.navigation.WinDiTopBar
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.ui.components.OtpTextField
import ru.alexsergeev.presentation.ui.components.SimpleTextButton
import ru.alexsergeev.presentation.utils.LockScreenOrientation
import ru.alexsergeev.presentation.viewmodel.CodeScreenViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun CodeScreen(
    navController: NavController,
    codeScreenViewModel: CodeScreenViewModel = koinViewModel()
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val codeValue = rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val isCodeValid by codeScreenViewModel.validateCode().collectAsStateWithLifecycle()

    val focusManager = LocalFocusManager.current
    val user by codeScreenViewModel.getUserData().collectAsStateWithLifecycle()

    val onValidateCode = {
        if (codeValue.value.length == 6) {
            scope.launch {
                codeScreenViewModel.validateCodeFlow(
                    "${user.phone.countryCode}${user.phone.basicNumber}",
                    codeValue.value.toString()
                )
            }
        } else {
            Toast.makeText(context, "Код должен содержать 6 цифр", Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(isCodeValid) {
        if (isCodeValid) {
            navController.navigate("main_screen")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        WinDiTopBar(
            navController = navController,
            text = "",
            needToBack = true
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 4.dp),
            text = stringResource(id = R.string.enter_code),
            style = WinDiTheme.typography.heading2,
            color = Color.Black
        )
        Text(
            modifier = Modifier
                .padding(top = 2.dp),
            text = stringResource(id = R.string.sent_code),
            style = WinDiTheme.typography.body2,
            color = Color.Black,
        )
        Text(
            modifier = Modifier
                .padding(bottom = 4.dp),
            text = "${user.phone.countryCode} ${user.phone.basicNumber}",
            style = WinDiTheme.typography.body2,
            color = Color.Black,
        )
        OtpTextField(
            otpText = codeValue.value,
            onOtpTextChange = { value, _ ->
                codeValue.value = value
                if (codeValue.value.length == 6) {
                    onValidateCode()
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth()
        )
        SimpleTextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = stringResource(id = R.string.sent_again),
            onClick = {
                focusManager.clearFocus()
            }
        )
    }
}