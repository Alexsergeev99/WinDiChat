package ru.alexsergeev.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.R
import ru.alexsergeev.presentation.states.MainScreenState
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.ui.components.ChannelListItem
import ru.alexsergeev.presentation.ui.components.Search
import ru.alexsergeev.presentation.viewmodel.MainScreenViewModel

@Composable
internal fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = koinViewModel()
) {
    val uiState = mainScreenViewModel.uiState.collectAsStateWithLifecycle(MainScreenState.Loading)
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Search(
                    hint = "Найти чаты",
                    text = textState,
                    onClickInMainScreenSearch = {
                        mainScreenViewModel.setSearchText(textState.value.text)
                        mainScreenViewModel.setFilteredChatsList()
                    }
                )
                Icon(
                    modifier = Modifier.clickable {
                        navController.navigate("profile_screen")
                    },
                    painter = painterResource(id = R.drawable.avatar_icon),
                    contentDescription = "user"
                )
            }
            when (val current = uiState.value) {
                is MainScreenState.Loading -> CircularProgressIndicator()

                is MainScreenState.Success -> {
                    LazyColumn(Modifier.fillMaxSize()) {
                        current.chats.forEach {
                            item {
                                ChannelListItem(
                                    chat = it,
                                    userId = it.secondUserId
                                ) {
                                    navController.navigate("messages_list/${it.secondUserId.toString()}")
                                }
                                HorizontalDivider()
                            }
                        }
                    }
                }

                is MainScreenState.EmptyList -> {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Таких чатов еще нет:(", style = WinDiTheme.typography.body1)
                    }
                }

                is MainScreenState.Error -> {
                    ErrorScreen {

                    }
                }
            }
        }
    }
}