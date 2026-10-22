package com.example.w3m.feature.groups.presentation.screen
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.w3m.feature.groups.domain.ChatType
import com.example.w3m.feature.groups.domain.GroupAction
import com.example.w3m.feature.groups.domain.GroupActionSheetState
import com.example.w3m.feature.groups.domain.GroupUiModel
import com.example.w3m.feature.groups.domain.MessagingPlatform
import com.example.w3m.feature.groups.presentation.component.GroupCard
import com.example.w3m.feature.groups.presentation.screen.botGroups.GroupActionBottomSheet
import com.example.w3m.feature.users.presentation.component.UsersHeader

@AndroidEntryPoint
class GroupsActivity : ComponentActivity()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupsGreeting(modifier: Modifier = Modifier, navController: NavController) {

    var sheetState by remember {
        mutableStateOf<GroupActionSheetState?>(null)
    }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        val groups = listOf(
            GroupUiModel(
                id = 1,
                name = "خودم گروه",
                username = "@my_group",
                chatType = ChatType.GROUP,
                platform = MessagingPlatform.BALE,
                count = 200
            ),
            GroupUiModel(
                id = 2,
                name = "گروه برنامه‌نویسی",
                username = "@programming_group",
                chatType = ChatType.CHANNEL,
                platform = MessagingPlatform.BALE,
                count = 2000
            ),
            GroupUiModel(
                id = 3,
                name = "گروه دوستان",
                username = "@friends_group",
                chatType = ChatType.GROUP,
                platform = MessagingPlatform.TELEGRAM,
                count = 20000
            ),
            GroupUiModel(
                id = 4,
                name = "گروه دوستان",
                username = "@friends_group",
                chatType = ChatType.CHANNEL,
                platform = MessagingPlatform.TELEGRAM,
                count = 10000000

            )
        )
        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color(0xFFF5FAFF)) {
            Column(modifier = modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
                ) {
                    item {
                        UsersHeader(
                            icon = Icons.Outlined.Face,
                            textHed = "فهرست کانال و گروه متصل",
                            textDirection = "همه کانالها و گروههای متصل را یکجا مدیریت کنید"
                        )
                    }
                    items(
                        items = groups,
                        key = { it.id },
                        contentType = { "group_card" }
                    ) { group ->
                        GroupCard(
                            groupName = group.name,
                            groupUsername = group.username,
                            type = group.chatType,
                            platform = group.platform,
                            count = group.count,
                            onMenuClick = {
                                // منوی همین گروه
                            },
                            onBotClick = {
                                sheetState = GroupActionSheetState(
                                    chatType = group.chatType,
                                    action = if (group.chatType == ChatType.GROUP) {
                                        GroupAction.BOT
                                    } else {
                                        GroupAction.AUTHOR
                                    }
                                )
                            },
                            onRulesClick = {
                                sheetState = GroupActionSheetState(
                                    chatType = group.chatType,
                                    action = if (group.chatType == ChatType.GROUP) {
                                        GroupAction.RULES
                                    } else {
                                        GroupAction.VIEWS
                                    }
                                )
                            }
                        )
                    }
                }
            }
            sheetState?.let { state ->
                GroupActionBottomSheet(
                    action = state.action,
                    chatType = state.chatType,
                    onDismiss = {
                        sheetState = null
                    },
                    onSave = {

                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun GroupsGreetingPreview() {
    val navController = rememberNavController()
    GroupsGreeting(navController = navController)
}
