package com.example.w3m.feature.main.presentation.screen

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.w3m.core.navigation.Nav
import com.example.w3m.feature.main.presentation.component.W3mLoadingScreen
import com.example.w3m.feature.main.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )

        window.isNavigationBarContrastEnforced = false

        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

        val mainViewModel: MainViewModel = hiltViewModel()
        val navController = rememberNavController()

        val isFirstOpen by mainViewModel.isFirstOpen.collectAsState(
            initial = null
        )

        AnimatedContent(
            targetState = isFirstOpen == null,
            modifier = modifier.fillMaxSize(),
            transitionSpec = {
                if (targetState) {
                    fadeIn(tween(350)) togetherWith fadeOut(tween(250))
                } else {
                    (
                            fadeIn(tween(600)) +
                                    scaleIn(
                                        initialScale = 0.92f,
                                        animationSpec = tween(600)
                                    )
                            ) togetherWith (
                            fadeOut(tween(350)) +
                                    scaleOut(
                                        targetScale = 1.06f,
                                        animationSpec = tween(350)
                                    )
                            )
                }
            },
            label = "SplashTransition"
        ) { loading ->
            if (loading) {
                W3mLoadingScreen(
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                when (isFirstOpen) {
                    true -> Nav(
                        navController = navController,
                        isFirstOpen = true
                    )

                    false -> Nav(
                        navController = navController,
                        isFirstOpen = false
                    )

                    null -> Unit
                }
            }
        }
    }
