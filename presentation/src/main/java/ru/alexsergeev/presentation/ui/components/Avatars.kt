package ru.alexsergeev.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
internal fun UserAvatar(image: String) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        model = image,
        contentScale = ContentScale.FillBounds,
        contentDescription = "avatar",
    )
}

@Composable
internal fun AvatarWithEdit(image: String, padding: Dp = 4.dp, editPhoto: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .padding(padding)
            .size(100.dp)
            .background(Color.Transparent)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            model = image,
            contentDescription = "avatar",
        )
        Icon(
            imageVector = Icons.Default.AddCircle,
            contentDescription = null,
            modifier = Modifier
                .background(Color.Transparent)
                .padding(horizontal = 6.dp)
                .size(20.dp)
                .align(Alignment.BottomEnd)
                .clickable { editPhoto() },
            tint = Color.Black
        )
    }
}