package ru.alexsergeev.presentation.ui.screens

import android.content.pm.ActivityInfo
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
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.navigation.WinDiTopBar
import ru.alexsergeev.presentation.ui.components.MessageInput
import ru.alexsergeev.presentation.ui.components.MessageList
import ru.alexsergeev.presentation.utils.LockScreenOrientation
import ru.alexsergeev.presentation.utils.rememberImeState
import ru.alexsergeev.presentation.viewmodel.MessagesListViewModel

@Composable
internal fun MessageListScreen(
    navController: NavController,
    userId: String,
    messagesListViewModel: MessagesListViewModel = koinViewModel()
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    val user = messagesListViewModel.getUserById(userId.toInt()).value

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WinDiTopBar(navController = navController, text = "${user.name.firstName} ${user.name.secondName}", needToBack = true, goToProfile = true)
        MessageList(
            navController = navController,
            userId
        )
        MessageInput()
        Spacer(modifier = Modifier.imePadding())
    }
}