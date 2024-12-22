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
    val categoryPainterResource = when (category) {
        "Health" -> painterResource(id = R.drawable.medical_hb)
        "Nutrition" -> painterResource(id = R.drawable.nutrition_hb)
        "Finance" -> painterResource(id = R.drawable.finance_hb)
        "Plants" -> painterResource(id = R.drawable.plants_hb)
        "Work" -> painterResource(id = R.drawable.work_hb)
        "Meditation" -> painterResource(id = R.drawable.meditation_hb)
        "Training" -> painterResource(id = R.drawable.training_hb)
        "Social" -> painterResource(id = R.drawable.social_hb)
        "Outdoor" -> painterResource(id = R.drawable.outdoor_hb)
        "Home" -> painterResource(id = R.drawable.home_hb)
        "Education" -> painterResource(id = R.drawable.education_hb)
        "Entertainment" -> painterResource(id = R.drawable.entertainment_hb)
        "Sport" -> painterResource(id = R.drawable.sport_hb)
        "Art" -> painterResource(id = R.drawable.art_hb)
        else -> painterResource(id = R.drawable.medical_hb)
    }
    val checkBoxPainterResource = when (status) {
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
            painter = categoryPainterResource,
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
            painter = checkBoxPainterResource,
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .clickable {
                    val newStatus = when (status) {
                        1 -> 0
                        0 -> 2
                        else -> 1
                    }
                    onStatusClick(newStatus)
                }
        )
    }
}
