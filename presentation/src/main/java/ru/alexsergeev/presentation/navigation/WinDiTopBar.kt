package ru.alexsergeev.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.alexsergeev.presentation.R
import ru.alexsergeev.presentation.theme.WinDiTheme

@Composable
internal fun WinDiTopBar(
    navController: NavController,
    text: String,
    needToBack: Boolean,
    needToEdit: Boolean = false,
    needToAdd: Boolean = false,
    needToSave: Boolean = false,
    goToProfile: Boolean = false,
    needToShare: Boolean = false,
    iAmGuest: Boolean = false,
    goToEditScreen: () -> Unit = { navController.navigate("edit_profile_screen") },
    goToProfileScreen: () -> Unit = { navController.navigate("profile_screen") },
    goToBackScreen: () -> Unit = { navController.navigateUp() },
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (needToBack) {
            Icon(
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp, end = 6.dp)
                    .clickable { goToBackScreen() },
                painter = painterResource(id = R.drawable.navigate_back),
                tint = WinDiTheme.colors.activeComponent,
                contentDescription = "back"
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 6.dp, bottom = 6.dp, start = 6.dp),
            text = text,
            color = Color.Black,
            style = WinDiTheme.typography.subheading1
        )
        if (needToEdit) {
            Icon(
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .clickable { goToEditScreen() },
                painter = painterResource(id = R.drawable.edit),
                tint = WinDiTheme.colors.activeComponent,
                contentDescription = "edit"
            )
        }
        if (needToSave) {
            Icon(
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .clickable { goToBackScreen() },
                painter = painterResource(id = R.drawable.check_mark),
                tint = WinDiTheme.colors.activeComponent,
                contentDescription = "save"
            )
        }
        if (needToAdd) {
            Icon(
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .clickable { },
                tint = WinDiTheme.colors.activeComponent,
                painter = painterResource(id = R.drawable.add),
                contentDescription = "add",
            )
        }
        if (iAmGuest) {
            Icon(
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp, end = 6.dp)
                    .clickable { },
                tint = WinDiTheme.colors.activeComponent,
                painter = painterResource(id = R.drawable.check_mark),
                contentDescription = "check_mark"
            )
        }
        if (needToShare) {
            Icon(
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp, end = 6.dp)
                    .clickable { },
                painter = painterResource(id = R.drawable.share),
                tint = WinDiTheme.colors.activeComponent,
                contentDescription = "share"
            )
        }
        if (goToProfile) {
            Icon(
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp, end = 6.dp)
                    .size(24.dp)
                    .clickable { goToProfileScreen() },
                painter = painterResource(id = R.drawable.avatar_icon),
                tint = WinDiTheme.colors.activeComponent,
                contentDescription = "avatar_icon"
            )
        }
    }
}