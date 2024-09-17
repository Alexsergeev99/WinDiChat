package ru.alexsergeev.windichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import ru.alexsergeev.presentation.navigation.Navigation
import ru.alexsergeev.presentation.theme.WinDiChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            WinDiChatTheme {
                Navigation()
            }
        }
    }
}
