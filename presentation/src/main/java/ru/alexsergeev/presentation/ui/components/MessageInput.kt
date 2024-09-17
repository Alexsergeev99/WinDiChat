package ru.alexsergeev.presentation.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.models.MessageUiModel
import ru.alexsergeev.presentation.utils.mock.messages
import ru.alexsergeev.presentation.viewmodel.MessagesListViewModel
import java.time.LocalDate

@Composable
internal fun MessageInput(
    messagesListViewModel: MessagesListViewModel = koinViewModel()
) {
    var inputValue by remember { mutableStateOf("") }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendMessage() {
        messagesListViewModel.sendMessage(
            MessageUiModel(
                id = messages.last().id + 1,
                senderId = 1,
                recipientId = 4,
                text = inputValue,
                date = LocalDate.now().toString()
            )
        )
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
