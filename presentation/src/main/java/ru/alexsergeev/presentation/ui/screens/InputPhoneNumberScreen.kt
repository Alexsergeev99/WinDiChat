package ru.alexsergeev.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.alexsergeev.presentation.R
import ru.alexsergeev.presentation.navigation.WinDiTopBar
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.ui.components.InputCodeCountryField
import ru.alexsergeev.presentation.ui.components.InputNumberTextField
import ru.alexsergeev.presentation.ui.components.InputPhoneNumberButtonChanger

private const val INPUT_PHONE_HINT = "999 999-99-99"

@Composable
internal fun InputPhoneNumberScreen(
    navController: NavController,
) {
    val checkPhoneNumberLength = remember {
        mutableStateOf(false)
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
            needToBack = false
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 4.dp),
            text = stringResource(id = R.string.enter_phone),
            style = WinDiTheme.typography.heading2,
            color = WinDiTheme.colors.activeComponent
        )
        Text(
            modifier = Modifier
                .padding(top = 2.dp),
            text = stringResource(id = R.string.we_will_sent_code),
            style = WinDiTheme.typography.subheading1,
            color = WinDiTheme.colors.activeComponent,
        )
        Text(
            modifier = Modifier
                .padding(bottom = 4.dp),
            text = stringResource(id = R.string.on_your_number),
            style = WinDiTheme.typography.subheading1,
            color = WinDiTheme.colors.activeComponent,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputCodeCountryField(onTextChange = {

            }
            )
            InputNumberTextField(hint = INPUT_PHONE_HINT, height = 40.dp, onTextChange = {

            }
            )
        }
        Spacer(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth()
        )
        InputPhoneNumberButtonChanger(navController, checkPhoneNumberLength)
    }
}