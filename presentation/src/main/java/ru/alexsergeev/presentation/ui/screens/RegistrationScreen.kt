package ru.alexsergeev.presentation.ui.screens

import android.content.pm.ActivityInfo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.ui.components.AvatarWithEdit
import ru.alexsergeev.presentation.ui.components.EditProfileButtonChanger
import ru.alexsergeev.presentation.ui.components.Search
import ru.alexsergeev.presentation.ui.components.TextArea
import ru.alexsergeev.presentation.utils.LockScreenOrientation
import ru.alexsergeev.presentation.utils.rememberImeState
import ru.alexsergeev.presentation.viewmodel.RegistrationScreenViewModel
import ru.alexsergeev.presentation.viewmodel.UserProfileViewModel

@Composable
internal fun RegistrationScreen(
    navController: NavController,
    registrationScreenViewModel: RegistrationScreenViewModel = koinViewModel()
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val needToEdit = remember {
        mutableStateOf(false)
    }
    val user = registrationScreenViewModel.getUserDataWithoutApi().collectAsStateWithLifecycle().value
    val startedAvatar = stringResource(id = R.string.mock_user_avatar)
    val changedAvatar = "https://www.1zoom.me/big2/62/199578-yana.jpg"

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
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
                    .padding(vertical = 8.dp)
                    .verticalScroll(scrollState),
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
                                registrationScreenViewModel.setUserData(
                                    user.copy(
                                        avatar = startedAvatar
                                    )
                                )
                            }

                            else -> {
                                registrationScreenViewModel.setUserData(
                                    user.copy(
                                        avatar = changedAvatar
                                    )
                                )
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${user.phone.countryCode}${user.phone.basicNumber}",
                    style = WinDiTheme.typography.subheading1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Search(
                    hint = "Имя",
                    isSearch = false,
                    padding = 6.dp,
                    onTextChange = {
                        registrationScreenViewModel.setUserData(
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
                        registrationScreenViewModel.setUserData(
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
                        registrationScreenViewModel.setUserData(
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
                        registrationScreenViewModel.setUserData(
                            user.copy(
                                city = it
                            )
                        )
                    },
                )
                TextArea(
                    hint = "Расскажи о себе",
                    onTextChange = {
                        registrationScreenViewModel.setUserData(
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
                EditProfileButtonChanger(navController)
            }
        }
    }
}