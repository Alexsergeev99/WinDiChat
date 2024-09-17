package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.models.ChatUiModel
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.utils.mock.messages
import ru.alexsergeev.presentation.viewmodel.MainScreenViewModel

@Composable
internal fun ChannelListItem(
    chat: ChatUiModel,
    userId: Int,
    mainScreenViewModel: MainScreenViewModel = koinViewModel(),
    onClick: () -> Unit = {}
) {

    val user = mainScreenViewModel.getUserById(userId).value

    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserAvatarSmall(user.avatar)
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = "${user.name.firstName} ${user.name.secondName}",
                style = WinDiTheme.typography.subheading2,
            )

            val lastMessageText = messages[chat.messagesId.last() - 1].text
            Text(
                text = lastMessageText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}