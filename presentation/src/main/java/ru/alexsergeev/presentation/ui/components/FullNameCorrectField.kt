package ru.alexsergeev.presentation.ui.components

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.models.UserUiModel
import ru.alexsergeev.presentation.viewmodel.UserProfileViewModel

@Composable
internal fun FullNameCorrectField(
    user: UserUiModel,
    userProfileViewModel: UserProfileViewModel = koinViewModel()
) {
    Search(
        user.name.firstName.ifBlank { "Имя Фамилия" },
        isSearch = false,
        onTextChange = {
            val fullName: List<String?> = it.split(" ")
            if (fullName.size == 1) {
                userProfileViewModel.setUserData(
                    user.copy(
                        name = FullName(fullName[0] ?: "Пользователь", ""),
                    )
                )
            } else {
                userProfileViewModel.setUserData(
                    user.copy(
                        name = FullName(
                            fullName[0] ?: "Пользователь",
                            fullName[1] ?: ""
                        ),
                    )
                )
            }
        }
    )
}