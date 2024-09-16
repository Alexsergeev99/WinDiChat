package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.getstream.sdk.chat.viewmodel.MessageInputViewModel
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory

@Composable
internal fun MessageInput(
    factory: MessageListViewModelFactory,
    messageInputViewModel: MessageInputViewModel = viewModel(factory = factory),
) {
    var inputValue by remember { mutableStateOf("") }

    fun sendMessage() {
        messageInputViewModel.sendMessage(inputValue)
        inputValue = ""
    }

    Row {
        TextField(
            modifier = Modifier.weight(1f),
            value = inputValue,
            onValueChange = { inputValue = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions { sendMessage() },
        )
        Button(
            modifier = Modifier.height(56.dp),
            onClick = { sendMessage() },
            enabled = inputValue.isNotBlank(),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Отправить"
            )
        }
    }
}
