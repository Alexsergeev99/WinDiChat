package ru.alexsergeev.windichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.alexsergeev.presentation.theme.WinDiChatTheme
import androidx.compose.material3.DropdownMenuItem
import ru.alexsergeev.presentation.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WinDiChatTheme {
                Navigation()
            }
        }
    }
}
