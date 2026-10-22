package com.example.w3m.feature.main.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan
import com.example.w3m.feature.main.presentation.bottomnav.BottomNavItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationBarItem(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    scope: CoroutineScope
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.White,
        tonalElevation = 0.dp
    ){
        val items = listOf(
            BottomNavItem.Chat,
            BottomNavItem.Bots,
            BottomNavItem.Channels,
            BottomNavItem.Users
        )

        items.forEachIndexed { index, item ->
            val scale = remember {
                Animatable(1f)
            }
            NavigationBarItem(
                selected = pagerState.currentPage == index,
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White),
                onClick = {
                    if (pagerState.currentPage != index) {

                        scope.launch {

                            scale.animateTo(
                                targetValue = 0.80f,
                                animationSpec = tween(80)
                            )

                            scale.animateTo(
                                targetValue = 1f,
                                animationSpec = tween(120)
                            )

                            pagerState.animateScrollToPage(index)

                        }

                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxSize(0.33f)
                            .padding(4.dp)
                            .graphicsLayer {
                                scaleX = scale.value
                                scaleY = scale.value
                            },
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color(0xFFE9F0FD)
                )
            )
        }
    }
}
