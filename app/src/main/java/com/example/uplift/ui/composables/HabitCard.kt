package com.example.uplift.ui.composables

// HabitCard.kt
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.uplift.R

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.tooling.preview.Preview
import com.example.uplift.data.models.Habit
import com.example.uplift.data.models.HabitLog
import java.time.LocalDate
import java.time.temporal.ChronoField

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitCard(habit: Habit = Habit(), habitLogList: List<HabitLog> = emptyList(), onClick : () -> Unit = {}, onStatusClick: (Int, Int) -> Unit = { _, _ -> }) {
    val currentDate = LocalDate.now()
    val firstDayOfWeek = currentDate.with(ChronoField.DAY_OF_WEEK, 1)
    val daysOfWeek = (0..6).map { firstDayOfWeek.plusDays(it.toLong()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .requiredWidthIn(min = 320.dp)
            .height(160.dp)
            .background(Color(0xFFf5feff), RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = habit.habit_name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    when (habit.frequency) {
                        1 -> "One time a week"
                        2 -> "Twice a week"
                        3 -> "Three times a week"
                        4 -> "Four times a week"
                        5 -> "Five times a week"
                        6 -> "Six times a week"
                        7 -> "Daily"
                        else -> "Unknown"
                    } + if (habit.time.isNotEmpty()) " at ${habit.time}" else "",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 3.dp),
                    color = Color(0xFF38AEB4)
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.seemore),
                    contentDescription = "See more",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(top = 6.dp)
                .padding(horizontal = 12.dp)
        ) {
            daysOfWeek.forEachIndexed { index, date ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        when (index + 2) {
                            2 -> "Mon"
                            3 -> "Tue"
                            4 -> "Wed"
                            5 -> "Thu"
                            6 -> "Fri"
                            7 -> "Sat"
                            8 -> "Sun"
                            else -> ""
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    HabitDateUnit(date, habitLogList[index], onStatusClick)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.attachment_hb),
                    contentDescription = "Attach",
                    modifier = Modifier.size(14.dp),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = habit.streak.toString(),
                    fontSize = 14.sp,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.check_hb),
                    contentDescription = "Complete",
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${habit.completion_rate.toInt()}%",
                    fontSize = 14.sp
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitDateUnit(date: LocalDate, habitLog: HabitLog, onStatusClick: (Int, Int) -> Unit) {
    val backgroundColor = when (habitLog.status) {
        0 -> Color(0xFFE45F5F) //undone
        1 -> Color(0xFF9AE45F) //done
        2 -> Color(0xFFe4c55f) //unchecked
        3 -> Color.Transparent //not there yet
        4 -> Color.Transparent //hidden
        else -> Color.Transparent
    }
    val borderColor = when (habitLog.status) {
        0 -> Color(0xFF7C0000) //undone
        1 -> Color(0xFF0E7C00) //done
        2 -> Color(0xFF7c6500) //unchecked
        3 -> Color(0xFF505050) //not there yet
        4 -> Color.Transparent //hidden
        else -> Color(0xFF505050)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(17.dp)
                )
                .background(backgroundColor, shape = RoundedCornerShape(17.dp))
                .clickable {
                val newStatus = when (habitLog.status) {
                    1 -> 0
                    0 -> 2
                    else -> 1
                }
                onStatusClick(habitLog.habitLog_id, newStatus)
            }
        ) {
            Text(
                text = date.dayOfMonth.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}