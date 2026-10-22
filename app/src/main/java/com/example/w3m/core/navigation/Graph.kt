package com.example.w3m.core.navigation
import kotlinx.serialization.Serializable


sealed class Graph {

    @Serializable
    data object Users : Graph()

    @Serializable
    data object Chat : Graph()

    @Serializable
    data object Bots : Graph()

    @Serializable
    data object Intro : Graph()

    @Serializable
    data object VerifyCode : Graph()

    @Serializable
    data object Channel : Graph()

    @Serializable
    data object Group : Graph()

    @Serializable
    data object Auth : Graph()

    @Serializable
    data object Main : Graph()


    @Serializable
    data object IntroNavHost : Graph()

    @Serializable
    data object MainNavHost : Graph()

    @Serializable
    data object AuthNavHost : Graph()
}
