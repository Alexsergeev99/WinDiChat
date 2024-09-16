package ru.alexsergeev.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.R
import ru.alexsergeev.presentation.navigation.WinDiTopBar
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.ui.components.UserAvatar
import ru.alexsergeev.presentation.ui.components.LogoButton
import ru.alexsergeev.presentation.viewmodel.UserProfileViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun ProfileScreen(
    navController: NavController,
    userProfileViewModel: UserProfileViewModel = koinViewModel()
) {

    val user by userProfileViewModel.getUserData().collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        WinDiTopBar(
            navController = navController,
            text = "Профиль",
            needToBack = true,
            needToEdit = true
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
            )
            UserAvatar(
                image = user.avatar,
                padding = 20.dp
            )
            Text(
                text = "${user.name.firstName} ${user.name.secondName}",
                color = Color.Black,
                style = WinDiTheme.typography.heading2
            )
            Text(
                text = "${user.phone.countryCode} ${user.phone.basicNumber}",
                color = Color.Black,
                style = WinDiTheme.typography.subheading2
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                LogoButton(R.drawable.behance_logo)
                LogoButton(R.drawable.pinterest_logo)
                LogoButton(R.drawable.linkedin_logo_new)
                LogoButton(R.drawable.vk_logo)
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                    )
                }
            }
        }
    }
}