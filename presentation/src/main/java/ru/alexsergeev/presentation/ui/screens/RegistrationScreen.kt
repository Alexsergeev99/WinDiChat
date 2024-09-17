package ru.alexsergeev.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.R
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.navigation.WinDiTopBar
import ru.alexsergeev.presentation.ui.components.AvatarWithEdit
import ru.alexsergeev.presentation.ui.components.EditProfileButtonChanger
import ru.alexsergeev.presentation.ui.components.Search
import ru.alexsergeev.presentation.ui.components.TextArea
import ru.alexsergeev.presentation.viewmodel.UserProfileViewModel

@Composable
internal fun RegistrationScreen(
    navController: NavController,
    userProfileViewModel: UserProfileViewModel = koinViewModel()
) {
    val needToEdit = remember {
        mutableStateOf(false)
    }
    val user = userProfileViewModel.getUserData().collectAsStateWithLifecycle().value
    val startedAvatar = stringResource(id = R.string.mock_user_avatar)
    val changedAvatar = "https://www.1zoom.me/big2/62/199578-yana.jpg"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        WinDiTopBar(
            navController = navController,
            text = "Регистрация",
            needToBack = true,
            needToShare = true
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AvatarWithEdit(
                user.avatar,
                padding = 20.dp,
                editPhoto = {
                    needToEdit.value = !needToEdit.value
                    when (needToEdit.value) {
                        true -> {
                            userProfileViewModel.setUserData(
                                user.copy(
                                    avatar = startedAvatar
                                )
                            )
                        }

                        else -> {
                            userProfileViewModel.setUserData(
                                user.copy(
                                    avatar = changedAvatar
                                )
                            )
                        }
                    }
                }
            )
            Search(
                hint = "Имя",
                isSearch = false,
                padding = 6.dp,
                onTextChange = {
                    userProfileViewModel.setUserData(
                        user.copy(
                            name = FullName(
                                it,
                                user.name.secondName
                            )
                        )
                    )
                },
            )
            Search(
                hint = "Фамилия",
                isSearch = false,
                padding = 6.dp,
                onTextChange = {
                    userProfileViewModel.setUserData(
                        user.copy(
                            name = FullName(user.name.firstName, it)
                        )
                    )
                },
            )
            Search(
                hint = "Ник",
                isSearch = false,
                padding = 6.dp,
                onTextChange = {
                    userProfileViewModel.setUserData(
                        user.copy(
                            username = it
                        )
                    )
                },
            )
            Search(
                hint = "Город",
                isSearch = false,
                padding = 6.dp,
                onTextChange = {
                    userProfileViewModel.setUserData(
                        user.copy(
                            city = it
                        )
                    )
                },
            )
            TextArea(
                hint = "Расскажи о себе",
                onTextChange = {
                    userProfileViewModel.setUserData(
                        user.copy(
                            info = it
                        )
                    )
                },
            )
            Spacer(
                modifier = Modifier
                    .height(48.dp)
            )
            EditProfileButtonChanger(navController, user)
        }
    }
}