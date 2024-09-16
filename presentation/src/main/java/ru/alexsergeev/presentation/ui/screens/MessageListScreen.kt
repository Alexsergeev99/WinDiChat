package ru.alexsergeev.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory
import ru.alexsergeev.presentation.ui.components.MessageInput
import ru.alexsergeev.presentation.ui.components.MessageList

@Composable
internal fun MessageListScreen(
    navController: NavController,
    cid: String,
) {
    Column(Modifier.fillMaxSize()) {
        val factory = MessageListViewModelFactory(cid)

        MessageList(
            navController = navController,
            factory = factory,
            modifier = Modifier.weight(1f),
        )
        MessageInput(factory = factory)
    }
}