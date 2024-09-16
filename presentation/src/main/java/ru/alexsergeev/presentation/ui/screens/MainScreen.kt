package ru.alexsergeev.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.navigation.WinDiTopBar
import ru.alexsergeev.presentation.states.MainScreenState
import ru.alexsergeev.presentation.ui.components.ChannelListItem
import ru.alexsergeev.presentation.viewmodel.MainScreenViewModel

@Composable
internal fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = koinViewModel()
) {
    val uiState = mainScreenViewModel.uiState.collectAsStateWithLifecycle(MainScreenState.Loading)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WinDiTopBar(
                navController = navController,
                text = "Мои чаты",
                needToBack = true,
                goToProfile = true
            )
            when (val current = uiState.value) {
                is MainScreenState.Loading -> CircularProgressIndicator()

                is MainScreenState.Success -> {
                    LazyColumn(Modifier.fillMaxSize()) {
                        current.chats.forEach {
                            item {
                                ChannelListItem(it) {
                                    navController.navigate("channel_item/${it.secondUserId}")
                                }
                                HorizontalDivider()
                            }
                        }
                    }
                }

                is MainScreenState.Error -> {
                    ErrorScreen{

                    }
                }
            }
        }
    }
}