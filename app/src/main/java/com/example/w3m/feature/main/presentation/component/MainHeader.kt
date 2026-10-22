package com.example.w3m.feature.main.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.w3m.R
import com.example.w3m.feature.main.presentation.screen.ui.theme.W3mTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHeader(
    modifier: Modifier = Modifier,
    name: String = "محمد حاجیلو",
    phone: String = "09376007032",
    balance: String = "9,610 تومان",
    onRefresh: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    showBalance: Boolean = false,
    showProfile: Boolean = true,
    showMenuButton: Boolean = false,
    onMenuClick: () -> Unit = {}
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        TopAppBar(
            title = {
                AnimatedContent(
                    targetState = showProfile,
                    transitionSpec = {
                        (
                            slideInHorizontally(
                                animationSpec = androidx.compose.animation.core.tween(500),
                                initialOffsetX = { width -> width / 2 }
                            ) + fadeIn()
                        ) togetherWith (
                            slideOutHorizontally(
                                animationSpec = androidx.compose.animation.core.tween(500),
                                targetOffsetX = { width -> -width / 2 }
                            ) + fadeOut()
                        )
                    },
                    label = "profile-icon-transition"
                ) { profileVisible ->
                    if (profileVisible) {
                        IconProfile()
                    }
                }
            },
            navigationIcon = {
                AnimatedContent(
                    targetState = showMenuButton,
                    transitionSpec = {
                        (
                            slideInHorizontally(
                                animationSpec = androidx.compose.animation.core.tween(500),
                                initialOffsetX = { width -> width / 2 }
                            ) + fadeIn()
                        ) togetherWith (
                            slideOutHorizontally(
                                animationSpec = androidx.compose.animation.core.tween(500),
                                targetOffsetX = { width -> -width / 2 }
                            ) + fadeOut()
                        )
                    },
                    label = "menu-icon-transition"
                ) { menuVisible ->
                    if (menuVisible) {
                        IconButton(
                            onClick = onMenuClick,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.menum),
                                contentDescription = "باز کردن منو",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp),
                                tint = Color(0xFF111827)
                            )
                        }
                    }
                }
            },
            actions = {
                IconW3m(showBalance = showBalance)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    W3mTheme {
        MainHeader()
    }
}
