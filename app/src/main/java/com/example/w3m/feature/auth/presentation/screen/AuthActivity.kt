package com.example.w3m.feature.auth.presentation.screen
import dagger.hilt.android.AndroidEntryPoint

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.w3m.R
import com.example.w3m.core.navigation.Graph
import com.example.w3m.feature.auth.presentation.component.PhoneTextField
import com.example.w3m.feature.auth.presentation.component.W3MButton
import com.example.w3m.feature.auth.presentation.component.WebsiteText
import com.example.w3m.feature.auth.presentation.screen.ui.theme.W3mTheme
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Gilroy
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
}

@Composable
fun AuthScreen(modifier: Modifier = Modifier, navController: NavController) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.White
        ) { innerPadding ->
            var phone by rememberSaveable(
                stateSaver = TextFieldValue.Saver
            ) {
                mutableStateOf(TextFieldValue(""))
            }

            Column(
                modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .imePadding()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                        .background(
                            color = Color.Black,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.logow),
                        contentDescription = "logo",
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = buildAnnotatedString {
                        append("W3M")
                        withStyle(
                            SpanStyle(
                                color = Color(0xFF3B82F6)
                            )
                        ) {
                            append(" AI")
                        }
                    },
                    modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = Gilroy
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "دستیار هوشمند شما",
                    modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = Yekan
                )
                Spacer(modifier = Modifier.height(40.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {

                    Text(
                        text = "شماره موبایل",
                        fontFamily = Yekan,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color(0xFF111827),
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 15.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    PhoneTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                        }
                    )

                }

                Spacer(modifier = Modifier.height(20.dp))

                W3MButton(
                    text = "دریافت کد",
                    {
                        navController.navigate(Graph.VerifyCode)
                        phone = TextFieldValue("")
                    }
                )
            }
            Box(modifier = modifier.navigationBarsPadding().fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                WebsiteText()
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun GreetingPreview() {
    W3mTheme {
        val navController = rememberNavController()
        AuthScreen(navController = navController)
    }
}
