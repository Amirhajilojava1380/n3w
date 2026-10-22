package com.example.w3m.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.w3m.feature.verification.presentation.screen.VerifyCodeScreen
import com.example.w3m.feature.verification.presentation.viewmodel.VerifyViewModel
import com.example.w3m.feature.auth.presentation.screen.AuthScreen
import com.example.w3m.feature.intro.presentation.screen.IntroScreen
import com.example.w3m.feature.intro.presentation.viewmodel.IntroViewModel
import com.example.w3m.feature.main.presentation.screen.MainPagerScreen


@Composable
fun Nav(navController: NavHostController, isFirstOpen: Boolean) {

    val startDestination =  if (isFirstOpen) Graph.MainNavHost else Graph.MainNavHost

    NavHost(
        navController = navController, startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(700)
            )
        }) {

        navigation<Graph.IntroNavHost>(
            startDestination = Graph.Intro
        ){
            composable<Graph.Intro> {
                val viewModel: IntroViewModel = hiltViewModel()

                IntroScreen(
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }

        navigation<Graph.AuthNavHost>(
            startDestination = Graph.Auth
        ){


            composable<Graph.Auth> {
                AuthScreen(navController = navController)
            }

            composable<Graph.VerifyCode> {
                val viewModel: VerifyViewModel = hiltViewModel()

                VerifyCodeScreen(navController = navController)
            }
        }

        navigation<Graph.MainNavHost>(
            startDestination = Graph.Main,
        ) {
            composable<Graph.Main> {

                MainPagerScreen(
                    navController = navController
                )
            }
        }
    }
}