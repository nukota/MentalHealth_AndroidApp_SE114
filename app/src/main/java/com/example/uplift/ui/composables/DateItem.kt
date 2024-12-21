package com.example.uplift.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateItem(date: LocalDate, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor1 = if (isSelected) Color(0xFF1D939A) else Color(0xff56CDD4)
    val backgroundColor2 = if (isSelected) Color(0xFF02777D) else Color(0xff02969E)
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(width = 50.dp, height = 62.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor1)
            .clickable { onClick() }
    ) {
        Text(
            text = date.dayOfWeek.name.take(3),
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = fontWeight,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 6.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(width = 50.dp, height = 36.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundColor2)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = date.dayOfMonth.toString(),
                color = Color.White,
                fontSize = 19.sp,
                fontWeight = fontWeight,
            )
        }
    }
}