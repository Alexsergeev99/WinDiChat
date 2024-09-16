package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.getstream.sdk.chat.adapter.MessageListItem
import io.getstream.chat.android.client.models.name
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.utils.cardShapeFor

@Composable
internal fun MessageCard(messageItem: MessageListItem.MessageItem) { // 1
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = when { // 2
            messageItem.isMine -> Alignment.End
            else -> Alignment.Start
        },
    ) {
        Card(
            modifier = Modifier.widthIn(max = 340.dp),
            shape = cardShapeFor(messageItem), // 3
            colors = CardDefaults.cardColors(
                containerColor = when {
                    messageItem.isMine -> WinDiTheme.colors.activeComponent
                    else -> WinDiTheme.colors.disabledText
                }
            ),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = messageItem.message.text,
                color = when {
                    messageItem.isMine -> WinDiTheme.colors.activeComponent
                    else -> WinDiTheme.colors.disabledText
                },
            )
        }
        Text(
            text = messageItem.message.user.name,
            fontSize = 12.sp,
        )
    }
}