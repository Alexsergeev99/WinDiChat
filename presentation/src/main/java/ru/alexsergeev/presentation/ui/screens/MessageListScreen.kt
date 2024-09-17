package ru.alexsergeev.presentation.ui.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.alexsergeev.presentation.navigation.WinDiTopBar
import ru.alexsergeev.presentation.ui.components.MessageInput
import ru.alexsergeev.presentation.ui.components.MessageList
import ru.alexsergeev.presentation.utils.rememberImeState

@Composable
internal fun MessageListScreen(
    navController: NavController,
    userId: String
) {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WinDiTopBar(navController = navController, text = "", needToBack = true, goToProfile = true)
        MessageList(
            navController = navController,
            userId
        )
        MessageInput()
        Spacer(modifier = Modifier.imePadding())
    }
}