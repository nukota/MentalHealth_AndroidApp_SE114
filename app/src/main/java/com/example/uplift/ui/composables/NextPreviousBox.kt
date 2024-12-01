package com.example.uplift.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R

@Composable
fun NextPreviousBox(modifier: Modifier ,text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(29.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .background(Color.White)
    ) {
        Text(
            text = text,
            color = Color(0xff505050),
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.inter)),
                fontSize = 17.sp
            ),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}
