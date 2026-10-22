package com.example.w3m.feature.main.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.w3m.feature.bots.presentation.screen.BotsGreeting
import com.example.w3m.feature.chat.presentation.screen.ChatScreen
import com.example.w3m.feature.groups.presentation.screen.GroupsGreeting
import com.example.w3m.feature.main.presentation.component.MainHeader
import com.example.w3m.feature.main.presentation.component.NavigationBarItem
import com.example.w3m.feature.users.presentation.screen.UsersGreeting

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainPagerScreen(
    navController: NavHostController
) {
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        val pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { 4 }
        )

        val scope = rememberCoroutineScope()
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current
        val isKeyboardVisible = WindowInsets.isImeVisible

        LaunchedEffect(pagerState.currentPage) {
            if (pagerState.currentPage != 0) {
                focusManager.clearFocus(force = true)
                keyboardController?.hide()
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),

            bottomBar = {
                if (!isKeyboardVisible) {
                    BottomAppBar(
                        modifier = Modifier.background(Color.Transparent),
                        containerColor = Color.Transparent,
                        tonalElevation = 0.dp
                    ) {
                        NavigationBarItem(
                            pagerState = pagerState,
                            scope = scope
                        )
                    }
                }
            },

            topBar = {
                AnimatedContent(
                    targetState = pagerState.currentPage == 0,
                    transitionSpec = {
                        (
                            slideInHorizontally(
                                initialOffsetX = { width -> width / 2 }
                            ) + fadeIn()
                        ) togetherWith (
                            slideOutHorizontally(
                                targetOffsetX = { width -> -width / 2 }
                            ) + fadeOut()
                        )
                    },
                    label = "main-header-transition"
                ) { isChatPage ->
                    if (isChatPage) {
                        MainHeader(
                            showProfile = false,
                            showMenuButton = true
                        )
                    } else {
                        MainHeader()
                    }
                }
            },


            ) { innerPadding ->
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                ) { page ->
                    when (page) {

                        1 -> {
                            BotsGreeting(
                                navController = navController
                            )
                        }

                        2 -> {
                            GroupsGreeting(
                                navController = navController
                            )
                        }

                        0 -> {
                            ChatScreen()
                        }

                        3 -> {
                            UsersGreeting(
                                navController = navController
                            )
                        }

                    }
                }
            }
        }
    }
}
