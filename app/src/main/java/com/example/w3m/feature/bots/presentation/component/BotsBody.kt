package com.example.w3m.feature.bots.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.w3m.feature.bots.domain.model.BotUiModel
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.BotButtonUiModel
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.BotPlatform
import com.example.w3m.feature.users.presentation.component.UsersHeader

@Composable
fun BotsBody(
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val botButtonUi = listOf(
        BotButtonUiModel(
            id = "1",
            title = "سلام خوبی چی خبره چی کارا میکنی من خوبم سلام خوبی چی خبره چی کارا میکنی من خوبم",
            isEnabled = true
        ),
        BotButtonUiModel(
            id = "2",
            title = "b",
            isEnabled = true
        ),
        BotButtonUiModel(
            id = "3",
            title = "c",
            isEnabled = false
        ),
        BotButtonUiModel(
            id = "4",
            title = "c",
            isEnabled = false
        ),
        BotButtonUiModel(
            id = "5",
            title = "c",
            isEnabled = false
        ),
        BotButtonUiModel(
            id = "6",
            title = "c",
            isEnabled = false
        ),

        )

    val bots = listOf(
        BotUiModel(
            name = "ah",
            username = "amirhajibot",
            isConnected = true,
            id = 1,
            BotPlatform.TELEGRAM
        ),
        BotUiModel(
            name = "ah",
            username = "amirhajibot",
            isConnected = true,
            id = 2,
            BotPlatform.WEB
        ),
        BotUiModel(
            name = "ah",
            username = "amirhajibot",
            isConnected = true,
            id = 3,
            BotPlatform.TELEGRAM
        ),
        BotUiModel(
            name = "ah",
            username = "amirhajibot",
            isConnected = true,
            id = 4,
            BotPlatform.TELEGRAM
        ),
        BotUiModel(
            name = "ah",
            username = "amirhajibot",
            isConnected = true,
            id = 5,
            BotPlatform.TELEGRAM
        )
    )
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 8.dp
        )
    ) {

        if (bots.isEmpty()) {

            item {
                FirstConnectionScreen(
                    modifier = Modifier.fillMaxWidth()
                )

            }

        } else {


            item {
                UsersHeader(
                    icon = Icons.Outlined.Build,
                    textHed = "مدیریت پلتفرم های متصل",
                    textDirection = "ربات‌های خود را متصل، تنظیم و مدیریت کنید"
                )
            }
            items(
                bots,
                key = { it.id }
            ) { bot ->
                BotCard(
                    bot = bot,
                    listBots = botButtonUi,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun BotsBodyScreenPreview() {
    BotsBody()
}
