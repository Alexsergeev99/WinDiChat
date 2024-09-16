package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.getstream.chat.android.ui.ChatUI.style
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.models.ChatUiModel
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.viewmodel.MainScreenViewModel

@Composable
internal fun ChannelListItem(
    chat: ChatUiModel,
    mainScreenViewModel: MainScreenViewModel = koinViewModel(),
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserAvatarSmall("https://www.1zoom.me/big2/62/199578-yana.jpg")
        Column(modifier = Modifier.padding(start = 8.dp)) { // 3
            Text(
                text = "${mainScreenViewModel.getUserById(chat.secondUserId).collectAsStateWithLifecycle().value.name.firstName} " +
                        mainScreenViewModel.getUserById(chat.secondUserId).collectAsStateWithLifecycle().value.name.secondName,
                style = WinDiTheme.typography.subheading2,
            )

            val lastMessageText = chat.messagesId.last().text
            Text(
                text = lastMessageText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}