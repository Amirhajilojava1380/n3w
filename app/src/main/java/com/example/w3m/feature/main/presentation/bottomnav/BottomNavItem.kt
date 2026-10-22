package com.example.w3m.feature.main.presentation.bottomnav

import com.example.w3m.R
import com.example.w3m.core.navigation.Graph

sealed class BottomNavItem(
    val title: String,
    val icon: Int,
    val graph: Graph
) {

    data object Chat : BottomNavItem(
        "چت",
        R.drawable.chat,
        graph = Graph.Chat
    )

    data object Bots : BottomNavItem(
        "پلتفرم",
        R.drawable.layers,
        graph = Graph.Bots
    )

    data object Users : BottomNavItem(
        "کاربران",
        R.drawable.group,
        graph = Graph.Users
    )
    data object Channels : BottomNavItem(
        "کانال",
        R.drawable.channels,
        graph = Graph.Channel
    )

}
