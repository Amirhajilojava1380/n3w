package com.example.w3m.feature.chat.presentation.screen

import android.Manifest
import android.graphics.Bitmap
import android.media.MediaRecorder
import android.net.Uri
import java.io.File
import kotlin.math.sin
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
import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.CornerRadius
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

private data class ChatMessage(
    val text: String,
    val isMine: Boolean
)

private data class ChatAttachment(
    val uri: Uri? = null,
    val bitmap: Bitmap? = null,
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
    val context = LocalContext.current
    var mediaRecorder by remember { mutableStateOf<MediaRecorder?>(null) }
    var recordingFile by remember { mutableStateOf<File?>(null) }
    var recordingSeconds by rememberSaveable { mutableStateOf(0) }

    fun startRecording() {
        if (mediaRecorder != null) return

        val outputFile = File(
            context.cacheDir,
            "voice_${System.currentTimeMillis()}.m4a"
        )

        try {
            MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setOutputFile(outputFile.absolutePath)
                prepare()
                start()
                mediaRecorder = this
                recordingFile = outputFile
                recordingSeconds = 0
                isListening = true
            }
        } catch (_: Exception) {
            mediaRecorder?.release()
            mediaRecorder = null
            outputFile.delete()
        }
    }

    fun stopRecording(saveRecording: Boolean) {
        val recorder = mediaRecorder ?: return
        try {
            recorder.stop()
        } catch (_: RuntimeException) {
            recordingFile?.delete()
        } finally {
            recorder.release()
            mediaRecorder = null
            if (saveRecording && recordingFile?.exists() == true) {
                messages += ChatMessage("پیام صوتی ضبط شد", isMine = true)
            } else {
                recordingFile?.delete()
            }
            recordingFile = null
            recordingSeconds = 0
            isListening = false
        }
    }

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
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null && attachments.size < 3) {
            attachments += ChatAttachment(bitmap = bitmap, isImage = true)
        }
        inputFocusRequester.requestFocus()
        keyboardController?.show()
    }
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            cameraLauncher.launch(null)
        }
    }
    val audioPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) startRecording()
    }
    val focusManager = LocalFocusManager.current
    val canSend = messageText.isNotBlank() || attachments.isNotEmpty()

    DisposableEffect(Unit) {
        onDispose {
            stopRecording(saveRecording = false)
            focusManager.clearFocus(force = true)
            keyboardController?.hide()
        }
    }

    LaunchedEffect(isListening) {
        if (isListening) {
            while (isActive) {
                delay(1000)
                recordingSeconds++
            }
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
                if (isListening) {
                    VoiceRecordingBar(
                        elapsedSeconds = recordingSeconds,
                        onCancel = { stopRecording(saveRecording = false) },
                        onStop = { stopRecording(saveRecording = true) }
                    )
                } else {
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
                                    "${index}_${attachment.uri}_${attachment.bitmap?.hashCode()}_${attachment.isImage}"
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
                                            model = attachment.bitmap ?: attachment.uri,
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
                                            .background(Color.White.copy(alpha = 0.9f), CircleShape)
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
                                            Box(
                                                modifier = Modifier
                                                    .size(36.dp)
                                                    .background(Color(0xFFEFF2F6), CircleShape),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.image),
                                                    contentDescription = null,
                                                    modifier = Modifier.size(21.dp),
                                                    tint = Color(0xFF111827)
                                                )
                                            }
                                        },

                                        onClick = {
                                            showAttachmentMenu = false
                                            imagePickerLauncher.launch("image/*")
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("انتخاب فایل", fontFamily = Yekan) },
                                        leadingIcon = {
                                            Box(
                                                modifier = Modifier
                                                    .size(36.dp)
                                                    .background(Color(0xFFEFF2F6), CircleShape),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.folder),
                                                    contentDescription = null,
                                                    modifier = Modifier.size(21.dp),
                                                    tint = Color(0xFF111827)
                                                )
                                            }
                                        },
                                        onClick = {
                                            showAttachmentMenu = false
                                            filePickerLauncher.launch(arrayOf("*/*"))
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("گرفتن عکس", fontFamily = Yekan) },
                                        leadingIcon = {
                                            Box(
                                                modifier = Modifier
                                                    .size(36.dp)
                                                    .background(Color(0xFFEFF2F6), CircleShape),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.camera),
                                                    contentDescription = null,
                                                    modifier = Modifier.size(21.dp),
                                                    tint = Color(0xFF111827)
                                                )
                                            }
                                        },
                                        onClick = {
                                            showAttachmentMenu = false
                                            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
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
                            if (canSend) {
                                sendMessage()
                            } else {
                                focusManager.clearFocus(force = true)
                                keyboardController?.hide()
                                audioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                            }
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = if (canSend) Color(0xFF3B82F6) else Color(0xFFEFF2F6),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            painter = painterResource(
                                if (canSend) {
                                    R.drawable.arrowupp
                                } else {
                                    R.drawable.mic
                                }
                            ),
                            contentDescription = if (canSend) {
                                "ارسال پیام"
                            } else {
                                "ضبط صدا"
                            },
                            modifier = Modifier.size(24.dp),
                            tint = when {
                                canSend -> Color.White
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
                        model = image.bitmap ?: image.uri,
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
private fun VoiceRecordingBar(
    elapsedSeconds: Int,
    onCancel: () -> Unit,
    onStop: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            onClick = onCancel,
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFEFF2F6), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = "لغو ضبط صدا",
                modifier = Modifier.size(26.dp),
                tint = Color(0xFF111827)
            )
        }

        VoiceWaveform(
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
        )

        Text(
            text = "%d:%02d".format(elapsedSeconds / 60, elapsedSeconds % 60),
            color = Color(0xFF596579),
            fontFamily = Yekan,
            fontSize = 15.sp
        )

        IconButton(
            onClick = onStop,
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFFF3B30), CircleShape)
        ) {
            Icon(
                painter = painterResource(R.drawable.stop),
                contentDescription = "توقف ضبط صدا",
                modifier = Modifier.size(20.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
private fun VoiceWaveform(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "voice-waveform")
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = (Math.PI * 2).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "voice-waveform-phase"
    )

    Canvas(modifier = modifier) {
        val barCount = 28
        val barWidth = 4.dp.toPx()
        val gap = 3.dp.toPx()
        val totalWidth = barCount * barWidth + (barCount - 1) * gap
        val startX = ((size.width - totalWidth) / 2f).coerceAtLeast(0f)

        repeat(barCount) { index ->
            val wave = ((sin(phase + index * 0.72f) + 1f) / 2f)
            val barHeight = 8.dp.toPx() + wave * (size.height - 8.dp.toPx())
            val x = startX + index * (barWidth + gap)
            val alpha = 0.45f + wave * 0.55f

            drawRoundRect(
                color = Color(0xFF3B82F6).copy(alpha = alpha),
                topLeft = Offset(x, (size.height - barHeight) / 2f),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(barWidth / 2f, barWidth / 2f)
            )
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



