package ru.alexsergeev.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory
import ru.alexsergeev.presentation.ui.components.MessageInput
import ru.alexsergeev.presentation.ui.components.MessageList

@Composable
internal fun MessageListScreen(
    navController: NavController,
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        MessageList(
            navController = navController,
        )
        MessageInput()
    }
}