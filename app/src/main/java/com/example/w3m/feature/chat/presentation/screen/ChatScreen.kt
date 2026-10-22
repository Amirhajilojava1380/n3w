package com.example.w3m.feature.chat.presentation.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import androidx.compose.foundation.background
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

private data class ChatMessage(
    val text: String,
    val isMine: Boolean
)

private data class ChatAttachment(
    val uri: Uri,
    val isImage: Boolean
)

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier
) {
    var messageText by rememberSaveable { mutableStateOf("") }
    var isListening by rememberSaveable { mutableStateOf(false) }
    var showAttachmentMenu by remember { mutableStateOf(false) }
    val messages = remember { mutableStateListOf<ChatMessage>() }
    val attachments = remember { mutableStateListOf<ChatAttachment>() }
    val inputFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var previewImage by remember { mutableStateOf<ChatAttachment?>(null) }

    fun addAttachments(uris: List<Uri>, isImage: Boolean) {
        val remainingSlots = 3 - attachments.size
        val existingUris = attachments.map { it.uri }.toSet()
        if (remainingSlots > 0) {
            attachments += uris
                .distinct()
                .filterNot { it in existingUris }
                .take(remainingSlots)
                .map { uri -> ChatAttachment(uri = uri, isImage = isImage) }
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        addAttachments(uris, isImage = true)
        inputFocusRequester.requestFocus()
        keyboardController?.show()
    }
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments()
    ) { uris: List<Uri> ->
        addAttachments(uris, isImage = false)
        inputFocusRequester.requestFocus()
        keyboardController?.show()
    }
    val focusManager = LocalFocusManager.current

    DisposableEffect(Unit) {
        onDispose {
            focusManager.clearFocus(force = true)
            keyboardController?.hide()
        }
    }

    fun sendMessage() {
        val message = messageText.trim()
        if (message.isNotEmpty() || attachments.isNotEmpty()) {
            val sentText = if (message.isNotEmpty()) message else "پیوست ارسال شد"
            messages += ChatMessage(text = sentText, isMine = true)
            messageText = ""
            attachments.clear()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5FAFF))
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

        androidx.compose.runtime.CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Ltr
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 0.dp)
                    .imePadding()
                    .navigationBarsPadding(),
                shape = RoundedCornerShape(32.dp),
                color = Color.White,
                border = BorderStroke(0.5.dp, Color(0xFFE1E5EA)),
                shadowElevation = 0.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (attachments.isNotEmpty()) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(88.dp)
                                .padding(start = 12.dp, end = 12.dp, top = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            itemsIndexed(
                                items = attachments,
                                key = { index, attachment ->
                                    "${index}_${attachment.uri}_${attachment.isImage}"
                                }
                            ) { _, attachment ->
                                Box(
                                    modifier = Modifier
                                        .size(72.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFFF1F3F5))
                                ) {
                                    if (attachment.isImage) {
                                        GlideImage(
                                            model = attachment.uri,
                                            contentDescription = "پیش‌نمایش تصویر",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clickable { previewImage = attachment },
                                            contentScale = ContentScale.Crop
                                        )
                                    } else {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.folder),
                                                contentDescription = "فایل انتخاب شده",
                                                modifier = Modifier.size(28.dp),
                                                tint = Color(0xFF111827)
                                            )
                                            Text(
                                                text = "فایل",
                                                fontFamily = Yekan,
                                                fontSize = 11.sp,
                                                color = Color(0xFF111827)
                                            )
                                        }
                                    }

                                    IconButton(
                                        onClick = { attachments.remove(attachment) },
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .size(24.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Close,
                                            contentDescription = "حذف پیوست",
                                            modifier = Modifier.size(16.dp),
                                            tint = Color(0xFF111827)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
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

                        androidx.compose.runtime.CompositionLocalProvider(
                            LocalLayoutDirection provides LayoutDirection.Rtl
                        ) {
                            MaterialTheme(
                                shapes = MaterialTheme.shapes.copy(
                                    extraSmall = RoundedCornerShape(20.dp)
                                )
                            ) {
                                DropdownMenu(
                                    expanded = showAttachmentMenu,
                                    onDismissRequest = { showAttachmentMenu = false },
                                    modifier = Modifier.background(Color.White)
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("انتخاب تصویر", fontFamily = Yekan) },
                                        leadingIcon = {
                                            Icon(
                                                painter = painterResource(R.drawable.image),
                                                contentDescription = null,
                                                modifier = Modifier.size(22.dp),
                                                tint = Color(0xFF111827)
                                            )
                                        },

                                        onClick = {
                                            showAttachmentMenu = false
                                            imagePickerLauncher.launch("image/*")
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("انتخاب فایل", fontFamily = Yekan) },
                                        leadingIcon = {
                                            Icon(
                                                painter = painterResource(R.drawable.folder),
                                                contentDescription = null,
                                                modifier = Modifier.size(22.dp),
                                                tint = Color(0xFF111827)
                                            )
                                        },
                                        onClick = {
                                            showAttachmentMenu = false
                                            filePickerLauncher.launch(arrayOf("*/*"))
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                inputFocusRequester.requestFocus()
                                keyboardController?.show()
                            },
                        color = Color.White,
                        shadowElevation = 0.dp
                    ) {
                        androidx.compose.runtime.CompositionLocalProvider(
                            LocalLayoutDirection provides LayoutDirection.Rtl
                        ) {
                            TextField(
                                value = messageText,
                                onValueChange = {
                                    messageText = it
                                    isListening = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .focusRequester(inputFocusRequester),
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
                                color = if (messageText.isNotBlank()) {
                                    Color(0xFF111111)
                                } else {
                                    Color(0xFFEFF2F6)
                                },
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            painter = painterResource(
                                if (messageText.isNotBlank()) {
                                    R.drawable.send_message
                                } else {
                                    R.drawable.mic
                                }
                            ),
                            contentDescription = if (messageText.isNotBlank()) {
                                "ارسال پیام"
                            } else {
                                "ضبط صدا"
                            },
                            modifier = Modifier.size(24.dp),
                            tint = when {
                                messageText.isNotBlank() -> Color.White
                                isListening -> Color(0xFF2563EB)
                                else -> Color(0xFF111827)
                            }
                        )
                    }
                    }
                }
            }
        }
    }

    previewImage?.let { image ->
        Dialog(
            onDismissRequest = { previewImage = null },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    GlideImage(
                        model = image.uri,
                        contentDescription = "تصویر بزرگ شده",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                    IconButton(
                        onClick = { previewImage = null },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .size(48.dp)
                            .background(Color.Black.copy(alpha = 0.55f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "بستن تصویر",
                            tint = Color.White
                        )
                    }
                }
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



