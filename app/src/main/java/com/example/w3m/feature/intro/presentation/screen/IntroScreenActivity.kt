package com.example.w3m.feature.intro.presentation.screen
import dagger.hilt.android.AndroidEntryPoint

import androidx.activity.ComponentActivity
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.w3m.R
import com.example.w3m.core.navigation.Graph
import com.example.w3m.feature.intro.domain.model.IntroModel
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan
import com.example.w3m.feature.intro.presentation.viewmodel.IntroViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IntroScreenActivity : ComponentActivity() {
}

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: IntroViewModel
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            val scope = rememberCoroutineScope()
            val pages = listOf(
                IntroModel(
                    R.drawable.intor1,
                    "ربات های تلگرام",
                    "بهترین ربات های تلگرام را پیدا کنید."
                ),
                IntroModel(
                    R.drawable.intro2,
                    "گروه و کانال",
                    "هزاران گروه و کانال را جستجو کنید."
                ),
                IntroModel(
                    R.drawable.intro3,
                    "تبلیغات",
                    "تبلیغ خود را ثبت کنید یا تبلیغات دیگران را ببینید."
                )
            )

            val pagerState = rememberPagerState {
                pages.size
            }

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding)
            ) {
                HorizontalPager(
                    state = pagerState
                ) { page ->
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = modifier.height(60.dp))
                        Image(
                            painter = painterResource(pages[page].image),
                            contentDescription = "",
                            modifier = modifier.fillMaxWidth(),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = modifier.height(80.dp))
                        Text(
                            text = pages[page].title,
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontFamily = Yekan,
                        )
                        Spacer(modifier = modifier.height(16.dp))
                        Text(
                            text = pages[page].description,
                            color = Color.Gray,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = Yekan,
                        )
                        Spacer(modifier = modifier.weight(1f))
                        Button(
                            onClick = {
                                scope.launch {

                                    if (pagerState.currentPage < pages.lastIndex) {

                                        pagerState.animateScrollToPage(
                                            pagerState.currentPage + 1,
                                            animationSpec = tween(
                                                durationMillis = 800,
                                                easing = FastOutSlowInEasing
                                            )

                                        )


                                    } else {
                                        viewModel.finishIntro()
                                        navController.navigate(Graph.Auth) {
                                            popUpTo(Graph.Intro) {
                                                inclusive = true
                                            }
                                        }
                                    }

                                }
                            },
                            modifier = modifier
                                .fillMaxWidth()
                                .height(18.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Blue
                            ),
                            shape = RoundedCornerShape(18.dp)

                        ) {

                            Text(
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Yekan,
                                text =
                                    if (pagerState.currentPage == pages.lastIndex)
                                        "شروع"
                                    else
                                        "بعدی"
                            )

                        }
                        Spacer(modifier = modifier.height(30.dp))
                    }
                }
            }
        }
    }
}
