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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.alexsergeev.presentation.models.MessageUiModel
import ru.alexsergeev.presentation.theme.WinDiTheme
import ru.alexsergeev.presentation.utils.cardShapeFor
import ru.alexsergeev.presentation.viewmodel.MainScreenViewModel

@Composable
internal fun MessageCard(
    messageUiModel: MessageUiModel,
    mainScreenViewModel: MainScreenViewModel = koinViewModel()
) {

    val mine = messageUiModel.senderId == 1
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
            )
        }
        Text(
            text = when {
                mine -> mainScreenViewModel.getUserById(messageUiModel.senderId).value.name.firstName

                else -> mainScreenViewModel.getUserById(messageUiModel.senderId).value.name.firstName
            },
            fontSize = 12.sp,
        )

    }
}