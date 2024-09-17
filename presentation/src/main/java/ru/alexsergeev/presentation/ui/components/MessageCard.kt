package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.models.MessageUiModel
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.utils.cardShapeFor
import ru.alexsergeev.presentation.viewmodel.MessagesListViewModel

@Composable
internal fun MessageCard(
    messageUiModel: MessageUiModel,
    userId: String,
    messagesListViewModel: MessagesListViewModel = koinViewModel()
) {

    val mine = messageUiModel.senderId == 1

    val secondUser = messagesListViewModel.getUserById(userId.toInt()).value
    val user = messagesListViewModel.getUserData().value


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = when {
            messageUiModel.senderId == 1 -> Alignment.End
            else -> Alignment.Start
        },
    ) {
        Card(
            modifier = Modifier.widthIn(max = 340.dp),
            shape = cardShapeFor(messageUiModel),
            colors = CardDefaults.cardColors(
                containerColor = when {
                    mine -> WinDiTheme.colors.activeComponent
                    else -> WinDiTheme.colors.disabledComponent
                }
            ),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = messageUiModel.text,
                color = when {
                    mine -> WinDiTheme.colors.disabledComponent
                    else -> WinDiTheme.colors.activeComponent
                },
                style = WinDiTheme.typography.body2
            )
        }
        Text(
            text = when {
                mine -> user.name.firstName
                else -> secondUser.name.firstName
            },
            style = WinDiTheme.typography.metadata1,
        )
    }
}