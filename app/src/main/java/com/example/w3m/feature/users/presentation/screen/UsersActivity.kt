package com.example.w3m.feature.users.presentation.screen
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.w3m.feature.users.domain.model.UserUiModel
import com.example.w3m.feature.users.presentation.component.UserCard
import com.example.w3m.feature.users.presentation.component.UserSearchField
import com.example.w3m.feature.users.presentation.component.UserSelectionActions
import com.example.w3m.feature.users.presentation.component.UserSendBottomSheet
import com.example.w3m.feature.users.presentation.component.UsersHeader

@AndroidEntryPoint
class UsersActivity : ComponentActivity() {
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UsersGreeting(modifier: Modifier = Modifier,navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var showConnectionSheet by remember { mutableStateOf(false) }
    var selectedUser by remember {
        mutableStateOf<UserUiModel?>(null)
    }
    val users = listOf(
        UserUiModel(
            id = 1L,
            name = "Amir 🌹",
            phone = "9899376007032",
            telegramUsername = "Amir_hajilio@"
        ),
        UserUiModel(
            id = 2L,
            name = "Ali",
            phone = "989123456789",
            telegramUsername = "Ali_telegram"
        ),
        UserUiModel(
            id = 3L,
            name = "Reza",
            phone = "989121234567",
            telegramUsername = "Reza_2026"
        ),
        UserUiModel(
            id = 4L,
            name = "Mohammad",
            phone = "989355551234",
            telegramUsername = "Mohammad_bot"
        ),
        UserUiModel(
            id = 5L,
            name = "Sara",
            phone = "989101112233",
            telegramUsername = "Sara_user"
        )
    )
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color(0xFFF5FAFF)) { innerPadding ->
            Column(modifier = modifier.fillMaxSize()){

                LazyColumn (
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
                ){

                    item {
                        UsersHeader(
                            icon = Icons.Outlined.Person,
                            textHed = "مدیریت کاربران",
                            textDirection = "جستجو و فیلتر کاربران گروه‌ها"
                        )
                        Column(
                            modifier = modifier
                                .clip(RoundedCornerShape(18.dp))
                                .background(Color.White)
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            UserSearchField(
                                value = searchText,
                                onValueChange = {
                                    searchText = it
                                }
                            )
                        }

                        UserSelectionActions(countUser = users.size)
                    }

                    items(
                        items = users,
                        key = { it.id }
                    ) { user ->
                        UserCard(
                            user = user,
                            onTelegramClick = {

                            },
                            onShowConnectionSheet = {
                                showConnectionSheet = true
                                selectedUser = user
                            }
                        )
                    }


                }



                if (selectedUser != null && showConnectionSheet) {
                    UserSendBottomSheet(
                        user = selectedUser!!,
                        onDismiss = {
                            selectedUser = null
                        },
                        onSave = {
                            selectedUser = null
                        }
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun UsersGreetingPreview() {
    val navController = rememberNavController()
    UsersGreeting(navController=navController)

}
