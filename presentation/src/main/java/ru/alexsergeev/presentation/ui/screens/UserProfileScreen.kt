package ru.alexsergeev.presentation.ui.screens

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
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
import ru.alexsergeev.presentation.utils.LockScreenOrientation
import ru.alexsergeev.presentation.viewmodel.UserProfileViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun UserProfileScreen(
    navController: NavController,
    userProfileViewModel: UserProfileViewModel = koinViewModel()
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val basicNumber = userProfileViewModel.getBasicNumber()
    val user by userProfileViewModel.getUserData(basicNumber).collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        WinDiTopBar(
            navController = navController,
            text = "Профиль",
            needToBack = true,
            needToEdit = true,
            goToBackScreen = {
                navController.navigate("main_screen")
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
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
            )
            Spacer(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "${user.name.firstName} ${user.name.secondName}".ifBlank { "Новый пользователь" },
                color = Color.Black,
                style = WinDiTheme.typography.heading2
            )
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "@${user.username}",
                color = Color.Black,
                style = WinDiTheme.typography.subheading1
            )
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = "${user.phone.countryCode} ${user.phone.basicNumber}",
                color = Color.Black,
                style = WinDiTheme.typography.subheading1
            )
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = user.city,
                color = Color.Black,
                style = WinDiTheme.typography.subheading1
            )
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = user.info,
                color = Color.Black,
                style = WinDiTheme.typography.subheading1
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                LogoButton(R.drawable.fb_logo)
                LogoButton(R.drawable.insta_logo)
                LogoButton(R.drawable.link_logo)
                LogoButton(R.drawable.twitter_logo)
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