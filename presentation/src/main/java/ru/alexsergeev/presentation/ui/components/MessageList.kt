package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.states.MessagesListState
import ru.alexsergeev.presentation.ui.screens.ErrorScreen
import ru.alexsergeev.presentation.viewmodel.MessagesListViewModel

@Composable
internal fun MessageList(
    navController: NavController,
    messagesListViewModel: MessagesListViewModel = koinViewModel()
) {
    val uiState by messagesListViewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.95f),
        contentAlignment = Alignment.Center
    ) {
        when (val current = uiState) {
            is MessagesListState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is MessagesListState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    reverseLayout = true,
                ) {
                    current.messages.filter { it.text.isNotBlank() }.forEach {
                        item {
                            MessageCard(it)
                        }
                    }
                }
            }

            is MessagesListState.Error -> {
                ErrorScreen {

                }
            }
        }
    }
}
