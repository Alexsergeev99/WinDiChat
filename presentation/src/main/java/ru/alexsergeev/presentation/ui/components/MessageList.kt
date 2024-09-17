package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.getstream.sdk.chat.adapter.MessageListItem
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory

@Composable
internal fun MessageList(
    modifier: Modifier = Modifier,
    navController: NavController,
    factory: MessageListViewModelFactory,
    messageListViewModel: MessageListViewModel = viewModel(factory = factory),
) {
    val state by messageListViewModel.state.observeAsState()
    val messageState = state ?: return

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (messageState) {
            is MessageListViewModel.State.Loading -> {
                CircularProgressIndicator()
            }

            is MessageListViewModel.State.NavigateUp -> {
                navController.popBackStack()
            }

            is MessageListViewModel.State.Result -> {
                val messageItems = messageState.messageListItem.items
                    .filterIsInstance<MessageListItem.MessageItem>()
                    .filter { it.message.text.isNotBlank() }
                    .asReversed()

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    reverseLayout = true,
                ) {
                    messageItems.forEach {
                        item {
                            MessageCard(it)
                        }
                    }
                }
            }
        }
    }
}
