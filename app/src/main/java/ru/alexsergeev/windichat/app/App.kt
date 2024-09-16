package ru.alexsergeev.windichat.app

import android.app.Application
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.client.models.image
import io.getstream.chat.android.client.models.name
import io.getstream.chat.android.livedata.ChatDomain
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.alexsergeev.data.di.dataModule
import ru.alexsergeev.domain.di.domainModule
import ru.alexsergeev.presentation.di.presentationModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(presentationModule, dataModule, domainModule)
        }

        val client = ChatClient.Builder(appContext = this, apiKey = "fwgq9nmm583a").build()

        val user = User().apply {
            id = "tutorial-droid"
            image = "https://bit.ly/2TIt8NR"
            name = "Tutorial Droid"
        }
        val token = client.devToken(user.id)

        ChatDomain.Builder(client, this)
            .offlineEnabled()
            .build()

        client.connectUser(
            user,
            token
        ).enqueue()

    }
}