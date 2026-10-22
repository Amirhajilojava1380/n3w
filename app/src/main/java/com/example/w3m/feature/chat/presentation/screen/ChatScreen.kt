package com.example.w3m.feature.chat.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardActions
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

private data class ChatMessage(
    val text: String,
    val isMine: Boolean
)

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier
) {
    var messageText by rememberSaveable { mutableStateOf("") }
    var isListening by rememberSaveable { mutableStateOf(false) }
    var showAttachmentMenu by remember { mutableStateOf(false) }
    val messages = remember { mutableStateListOf<ChatMessage>() }

    fun sendMessage() {
        val message = messageText.trim()
        if (message.isNotEmpty()) {
            messages += ChatMessage(text = message, isMine = true)
            messageText = ""
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5FAFF))
            .imePadding()
            .navigationBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            if (messages.isEmpty()) {
                Text(
                    text = "پیام خود را بنویسید",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF8B96A7),
                    fontFamily = Yekan,
                    fontSize = 16.sp
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    reverseLayout = true
                ) {
                    items(messages.asReversed()) { message ->
                        MessageBubble(message)
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box {
                IconButton(
                    onClick = { showAttachmentMenu = true },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFFEFF2F6), CircleShape)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add),
                        contentDescription = "افزودن فایل",
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF111827)
                    )
                }

                DropdownMenu(
                    expanded = showAttachmentMenu,
                    onDismissRequest = { showAttachmentMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("انتخاب تصویر", fontFamily = Yekan) },
                        onClick = { showAttachmentMenu = false }
                    )
                    DropdownMenuItem(
                        text = { Text("انتخاب فایل", fontFamily = Yekan) },
                        onClick = { showAttachmentMenu = false }
                    )
                }
            }

            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(28.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                TextField(
                    value = messageText,
                    onValueChange = {
                        messageText = it
                        isListening = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = if (isListening) "در حال ضبط صدا..." else "پیام خود را بنویسید...",
                            fontFamily = Yekan,
                            fontSize = 16.sp,
                            color = Color(0xFF8B96A7)
                        )
                    },
                    singleLine = false,
                    maxLines = 4,
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontFamily = Yekan,
                        fontSize = 16.sp,
                        color = Color(0xFF111827)
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = { sendMessage() }),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }

            IconButton(
                onClick = {
                    if (messageText.isNotBlank()) {
                        sendMessage()
                    } else {
                        isListening = !isListening
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = if (messageText.isNotBlank()) Color(0xFF111111) else Color(0xFFEFF2F6),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    painter = painterResource(
                        if (messageText.isNotBlank()) R.drawable.send_message else R.drawable.mic
                    ),
                    contentDescription = if (messageText.isNotBlank()) "ارسال پیام" else "ضبط صدا",
                    modifier = Modifier.size(24.dp),
                    tint = if (messageText.isNotBlank()) Color.White else Color(0xFF111827)
                )
            }
        }
    }
}

@Composable
private fun MessageBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isMine) Arrangement.Start else Arrangement.End
    ) {
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = if (message.isMine) Color(0xFF111111) else Color.White,
            shadowElevation = 1.dp
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                color = if (message.isMine) Color.White else Color(0xFF111827),
                fontFamily = Yekan,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}
