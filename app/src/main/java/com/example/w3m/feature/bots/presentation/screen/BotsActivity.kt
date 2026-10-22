package com.example.w3m.feature.bots.presentation.screen
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.w3m.feature.bots.presentation.component.fab.BotConnectionBottomSheet
import com.example.w3m.feature.bots.presentation.component.BotsBody
import com.example.w3m.feature.bots.presentation.component.fab.ExpandableFab
import com.example.w3m.feature.bots.presentation.screen.theme.W3mTheme

@AndroidEntryPoint
class BotsActivity : ComponentActivity()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BotsGreeting(modifier: Modifier = Modifier, navController: NavController) {
    var showConnectionSheet by remember {
        mutableStateOf(false)
    }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color(0xFFF3F8FD)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                BotsBody()
                ExpandableFab(
                    navController = navController,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(
                            start = 8.dp,
                            bottom = 8.dp
                        ),
                    onConnectClick = {
                        showConnectionSheet = true
                    }
                )
            }

        }
    }

    if (showConnectionSheet) {
        BotConnectionBottomSheet(
            onDismiss = {
                showConnectionSheet = false
            }
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun BotsScreenPreview() {
    val navController = rememberNavController()
    BotsGreeting(navController = navController)
}
