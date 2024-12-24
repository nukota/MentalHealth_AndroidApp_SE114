package com.example.uplift.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R

@Composable
fun AnswerOption(
    text: String,
    iconResId: Int,
    isSelected: Boolean,
    clickable: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isSelected) Color.White else Color(0xffd7f9fa)
            )
            .clickable { clickable() }
            .height(40.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = text,
                tint = Color(0xfff55d5f),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(20.dp)
            )

            Text(
                text = text,
                color = Color.Black,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }
}