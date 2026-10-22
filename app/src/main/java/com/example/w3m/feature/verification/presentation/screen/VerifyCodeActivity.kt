package com.example.w3m.feature.verification.presentation.screen
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.w3m.core.navigation.Graph
import com.example.w3m.feature.verification.presentation.component.VerifyBox
import com.example.w3m.feature.verification.presentation.component.VerifyButton
import com.example.w3m.feature.verification.presentation.component.VerifyHeader
import com.example.w3m.feature.verification.presentation.component.VerifyTextField
import com.example.w3m.feature.verification.presentation.screen.ui.theme.W3mTheme
import com.example.w3m.feature.verification.presentation.viewmodel.VerifyViewModel
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan
import kotlin.also

@AndroidEntryPoint
class VerifyCodeActivity : ComponentActivity() {
}

@Composable
fun VerifyCodeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    //viewModel: VerifyViewModel
) {
//    LaunchedEffect(Unit) {
//        viewModel.startSmsListener()
//    }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = Color.White
        ) { innerPadding ->
            var fullName by rememberSaveable { mutableStateOf("") }
            var otp by rememberSaveable { mutableStateOf(List(5) { "" }) }
            val focusRequesters = remember { List(5) { FocusRequester() } }
            var canResend by rememberSaveable { mutableStateOf(false) }
            var timerKey by rememberSaveable { mutableIntStateOf(0) }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .imePadding()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                VerifyHeader(
                    "09376007032",
                    onChengPhon = {
                        navController.popBackStack()
                    },
                    onFinish = {
                        canResend = true
                    },
                    timerKey = timerKey
                )
                Spacer(modifier = modifier.height(15.dp))

                Column(
                    modifier = modifier.fillMaxWidth(0.9f)
                ) {
                    VerifyTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = "نام و نام خانوادگی",
                        placeholder = "مثلاً علی رضایی",
                        leadingIcon = Icons.Rounded.Person
                    )
                }

                Spacer(modifier = modifier.height(26.dp))

                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "کد تایید",
                        fontFamily = Yekan,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF111827),
                        modifier = modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = modifier.height(20.dp))

                    CompositionLocalProvider(
                        LocalLayoutDirection provides LayoutDirection.Ltr
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = modifier
                                .wrapContentSize()
                                .align(Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            otp.forEachIndexed { index, value ->
                                VerifyBox(

                                    value = value,

                                    onValueChange = { newValue ->
                                        otp = otp.toMutableList().also {
                                            it[index] = newValue
                                        }
                                    },

                                    focusRequester = focusRequesters[index],

                                    onNext = {

                                        if (index < 4)
                                            focusRequesters[index + 1].requestFocus()

                                    },

                                    onPrevious = {

                                        if (index > 0)
                                            focusRequesters[index - 1].requestFocus()

                                    }
                                )
                            }

                        }
                    }

                    Spacer(modifier = modifier.height(25.dp))

                    VerifyButton(
                        text = "تایید و ورود",
                        onClick = {
                            navController.navigate(Graph.MainNavHost){
                                popUpTo(Graph.AuthNavHost) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                            fullName = ""
                            otp = List(5) { "" }
                        }
                    )

                    Spacer(modifier = modifier.height(25.dp))

                    Text(
                        text = "ارسال مجدد",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Yekan,
                        color = if (canResend)
                            Color(0xFF2563EB)
                        else
                            Color.Red,
                        modifier = modifier
                            .fillMaxWidth(0.3f)
                            .clip(RoundedCornerShape(18.dp))
                            .clickable(enabled = canResend) {
                                canResend = false
                                timerKey++
                            },
                        textAlign = TextAlign.Center
                    )
                }

            }

        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun GreetingPreview() {
    W3mTheme {
        val navController = rememberNavController()
        VerifyCodeScreen(navController = navController)
    }
}
