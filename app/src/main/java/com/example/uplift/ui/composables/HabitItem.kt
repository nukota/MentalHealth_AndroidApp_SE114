package com.example.uplift.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R

@Composable
fun HabitItem(habitLogId: Int, habitName: String, time: String, status: Int, category: String, onStatusClick: (Int) -> Unit) {
    var currentStatus by remember { mutableStateOf(status) }
    val painterResource = when (currentStatus) {
        1 -> painterResource(id = R.drawable.completed_box)
        0 -> painterResource(id = R.drawable.uncompleted_box)
        else -> painterResource(id = R.drawable.unchecked_box)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.work_hb),
            contentDescription = null,
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Column(
            modifier = Modifier
                .padding(start = 20.dp, bottom = 4.dp)
        ) {
            Text(
                habitName,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                time,
                color = Color.Gray,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource,
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .clickable {
                    val newStatus = when (currentStatus) {
                        1 -> 0
                        0 -> 2
                        else -> 1
                    }
                    currentStatus = newStatus
                    onStatusClick(newStatus)
                }
        )
    }
}
