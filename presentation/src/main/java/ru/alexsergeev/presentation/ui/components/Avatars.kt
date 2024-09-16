package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
internal fun UserAvatarSmall(image: String) {
    AsyncImage(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape),
        model = image,
        contentDescription = "avatar",
    )
}

@Composable
internal fun UserAvatar(image: String, padding: Dp = 4.dp) {
    AsyncImage(
        modifier = Modifier
            .padding(padding)
            .size(200.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
        model = image,
        contentDescription = "avatar",
    )
}