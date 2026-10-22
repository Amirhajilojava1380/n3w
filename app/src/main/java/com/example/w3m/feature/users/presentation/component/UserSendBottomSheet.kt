package com.example.w3m.feature.users.presentation.component
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.StartMessageEditor
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.StartMessageFilePicker
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan
import com.example.w3m.feature.users.domain.model.UserUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSendBottomSheet(
    user: UserUiModel,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(
            topStart = 22.dp, topEnd = 22.dp
        ),
        dragHandle = null,
        modifier = Modifier.heightIn(max = 700.dp),
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "ارسال پیام",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = Yekan,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "پیامی که الان ارسال میشود",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = Yekan,
                fontSize = 12.sp,
                color = Color(0xFF6B7280)
            )


            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(12.dp), verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize(0.15f)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .background(Color(0xFFE9EDF2)), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.userr),
                        contentDescription = "تصویر کاربر",
                        tint = Color(0xFF7B818A),
                        modifier = Modifier.fillMaxSize(0.7f)
                    )
                }

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Column {

                    Text(
                        text = user.name,
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111827)
                    )

                    Text(
                        text = user.phone,
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        maxLines = 1,
                        color = PrimaryBlue,
                        modifier = Modifier.wrapContentHeight(),
                        textAlign = TextAlign.Center
                    )

                }

            }
            var startMessage by remember {
                mutableStateOf("")
            }
            var selectedFileUri by remember {
                mutableStateOf<Uri?>(null)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.Start
            ) {
                StartMessageEditor(
                    value = startMessage, onValueChange = { startMessage = it })
                StartMessageFilePicker(
                    onFileSelected = { uri ->
                        selectedFileUri = uri
                    })
            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(
                        horizontal = 18.dp, vertical = 14.dp
                    ), horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                OutlinedButton(
                    onClick = {
                        onDismiss()
                    },
                    modifier = Modifier
                        .weight(0.75f)
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(
                        1.dp, Color(0xFF2563EB)
                    )
                ) {
                    Text(
                        text = "انصراف",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        color = Color(0xFF2563EB)
                    )
                }


                Button(
                    onClick = onSave,
                    modifier = Modifier
                        .weight(1.8f)
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2563EB)
                    )
                ) {

                    Text(
                        text = "ارسال پیام",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
