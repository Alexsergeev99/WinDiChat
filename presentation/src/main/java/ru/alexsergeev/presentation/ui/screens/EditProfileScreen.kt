package ru.alexsergeev.presentation.ui.screens

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import ru.alexsergeev.presentation.navigation.WinDiTopBar
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.ui.components.FullNameCorrectField
import ru.alexsergeev.presentation.ui.components.RemoveProfileButton
import ru.alexsergeev.presentation.ui.components.Search
import ru.alexsergeev.presentation.ui.components.TextArea
import ru.alexsergeev.presentation.ui.components.UserAvatar
import ru.alexsergeev.presentation.utils.LockScreenOrientation
import ru.alexsergeev.presentation.viewmodel.UserProfileViewModel

@Composable
internal fun EditProfileScreen(
    navController: NavController,
    userProfileViewModel: UserProfileViewModel = koinViewModel()
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val user by userProfileViewModel.getUserData().collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            UserAvatar(user.avatar)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                WinDiTopBar(
                    navController = navController,
                    text = "",
                    needToBack = true,
                    needToSave = true
                )
            }
        }
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                FullNameCorrectField(user)
            }
            item {
                Search(user.city.ifBlank { "Город" }, isSearch = false, onTextChange = {
                    userProfileViewModel.setUserData(
                        user.copy(
                            city = it,
                        )
                    )
                }
                )
            }
            item {
                TextArea(user.info.ifBlank { "Расскажите о себе" }, onTextChange = {
                    userProfileViewModel.setUserData(
                        user.copy(
                            info = it,
                        )
                    )
                })
            }
            item {
                Spacer(Modifier.height(24.dp))
            }
            item {
                RemoveProfileButton(navController)
            }
        }
    }
}