package com.example.w3m.feature.users.presentation.component
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

private val TextPrimary = Color(0xFF111827)
private val TextSecondary = Color(0xFF64748B)
private val FilterBackground = Color(0xFFF1F5FB)

@Composable
fun BotSearchSection(
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedFilter by remember {
        mutableStateOf(SearchFilter.ALL)
    }

    Column(
        modifier = modifier.fillMaxWidth().padding(18.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = onSearchClick,
                modifier = modifier
                    .fillMaxWidth()
                    .height(14.dp)
                    .animateContentSize()
                ,
                singleLine = true,
                textStyle = TextStyle(
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Right,
                    lineHeight = 24.sp
                ),


                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),

                placeholder = {
                    Text(
                        "جستجو",
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                    )
                },

                leadingIcon = {

                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxSize(0.1f)
                            .clip(RoundedCornerShape(22.dp))
                            .background(Color(0xFFDCEAFF))
                            .aspectRatio(1f)
                    ) {
                        Icon(
                            modifier = modifier.size(20.dp),
                            painter = painterResource(R.drawable.search),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }

                },
                colors = OutlinedTextFieldDefaults.colors(

                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,

                    focusedBorderColor = Color(0xFF3B82F6),
                    unfocusedBorderColor = Color(0xFFDCEAFF),

                    cursorColor = Color(0xFF3B82F6)

                ),

                shape = RoundedCornerShape(18.dp)
            )

        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

//        SearchFilters(
//            selectedFilter = selectedFilter,
//            onFilterSelected = {
//                selectedFilter = it
//            }
//        )
    }
}

enum class SearchFilter {
    ALL,
    NAME,
    MOBILE,
    ID
}

data class SearchFilterUiModel(
    val title: String,
    val count: Int,
    val filter: SearchFilter
)

val filters = listOf(
    SearchFilterUiModel(
        title = "همه",
        count = 2,
        filter = SearchFilter.ALL
    ),
    SearchFilterUiModel(
        title = "نام",
        count = 12,
        filter = SearchFilter.NAME
    ),
    SearchFilterUiModel(
        title = "موبایل",
        count = 1,
        filter = SearchFilter.MOBILE
    ),
    SearchFilterUiModel(
        title = "آیدی",
        count = 0,
        filter = SearchFilter.ID
    )
)
@Composable
private fun SearchFilters(
    selectedFilter: SearchFilter,
    onFilterSelected: (SearchFilter) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (item in filters) {
            SearchFilterItem(
                text = "${item.title} (${item.count})",
                filter = item.filter,
                selectedFilter = selectedFilter,
                onClick = onFilterSelected,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun SearchFilterItem(
    text: String,
    filter: SearchFilter,
    selectedFilter: SearchFilter,
    onClick: (SearchFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    val isSelected = filter == selectedFilter

    Box(
        modifier = modifier
            .fillMaxHeight(0.07f)
            .clip(RoundedCornerShape(22.dp))
            .background(
                if (isSelected) {
                    PrimaryBlue
                } else {
                    Color(0xFFF8FBFF)
                }
            )
            .border(width = 0.5.dp, color = Color(0xFFDCEAFF),shape = RoundedCornerShape(22.dp))
        .clickable {
                onClick(filter)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontFamily = Yekan,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) {
                Color.White
            } else {
                TextSecondary
            },
            textAlign = TextAlign.Center
        )
    }
}
