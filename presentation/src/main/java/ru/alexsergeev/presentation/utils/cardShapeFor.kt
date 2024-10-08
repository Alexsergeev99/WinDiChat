package ru.alexsergeev.presentation.utils

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.getstream.sdk.chat.adapter.MessageListItem
import ru.alexsergeev.presentation.models.MessageUiModel

@Composable
internal fun cardShapeFor(
    messageUiModel: MessageUiModel
//    message: MessageListItem.MessageItem
): Shape {
    val roundedCorners = RoundedCornerShape(16.dp)
    return when {
        messageUiModel.senderId == 1 -> roundedCorners.copy(bottomEnd = CornerSize(0))
        else -> roundedCorners.copy(bottomStart = CornerSize(0))
    }
}