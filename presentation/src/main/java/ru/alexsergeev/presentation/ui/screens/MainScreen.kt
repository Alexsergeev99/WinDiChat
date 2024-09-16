package ru.alexsergeev.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.navigation.WinDiTopBar
import ru.alexsergeev.presentation.ui.components.ChannelListItem
import ru.alexsergeev.presentation.utils.mock.chats
import ru.alexsergeev.presentation.viewmodel.MainScreenViewModel

@Composable
internal fun MainScreen(
    navController: NavController,
    channelListViewModel: ChannelListViewModel = viewModel(
        factory = ChannelListViewModelFactory()
    ),
    mainScreenViewModel: MainScreenViewModel = koinViewModel()
) {
    val state by channelListViewModel.state.observeAsState()
    val channelState = state

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (channelState != null) {
            if (channelState.isLoading) {
                CircularProgressIndicator()
            } else {
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
                    LazyColumn(Modifier.fillMaxSize()) {
                        chats.forEach {
                            item {
                                ChannelListItem(it) {
                                    navController.navigate("channel_item/${it.secondUserId}")
                                }
                                Divider()
                            }
                        }
                    }
                }
            }
        }
    }
}