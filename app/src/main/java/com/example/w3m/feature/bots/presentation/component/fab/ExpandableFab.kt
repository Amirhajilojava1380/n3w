package com.example.w3m.feature.bots.presentation.component.fab
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.w3m.R
import com.example.w3m.feature.bots.presentation.component.MenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableFab(
    navController: NavController,
    modifier: Modifier = Modifier,
    onReportClick: () -> Unit = {},
    onConnectClick: () -> Unit = {},
    onStudioClick: () -> Unit = {}
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        expanded = false
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + slideInVertically(
                initialOffsetY = { it / 2 }
            ),
            exit = fadeOut() + slideOutVertically(
                targetOffsetY = { it / 2 }
            )
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                MenuItem(
                    icon = R.drawable.chart,
                    text = "گزارش",
                    onClick = onReportClick
                )

                MenuItem(
                    icon = R.drawable.link,
                    text = "اتصال پلتفرم جدید",
                    onClick = onConnectClick
                )

                MenuItem(
                    icon = R.drawable.image,
                    text = "استدیوی تصویرسازی",
                    onClick = onStudioClick
                )
            }
        }

        FloatingActionButton(
            onClick = {
                expanded = !expanded
            },
            modifier = Modifier.fillMaxWidth(0.14f).widthIn(
                min = 18.dp,
                max = 72.dp
            ).aspectRatio(1f),
            containerColor = Color.White,
            contentColor = Color(0xFF172554),
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 1.dp
            )
        ) {

            AnimatedContent(
                targetState = expanded,
                label = "fab_icon"
            ) { isExpanded ->

                Icon(
                    imageVector = if (isExpanded)
                        Icons.Default.Close
                    else
                        Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(0.5f)
                )
            }
        }
    }
}
