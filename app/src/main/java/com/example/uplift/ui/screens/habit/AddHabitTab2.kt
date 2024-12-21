package com.example.uplift.ui.screens.habit

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

@SuppressLint("DefaultLocale")
@Composable
fun AddHabitTab2(
    habitName: String,
    habitTime: String,
    onHabitNameChange: (String) -> Unit,
    onHabitTimeChange: (String) -> Unit
) {
    var timeInput by remember { mutableStateOf(habitTime) }
    var isValidTime by remember { mutableStateOf(true) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(344.dp)
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "Define your habit",
            fontWeight = FontWeight.Medium,
            style = TextStyle(fontSize = 18.sp, color = Color(0xFF00B5AD)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(vertical = 20.dp)
        )

        OutlinedTextField(
            value = habitName,
            onValueChange = onHabitNameChange,
            label = {
                Text(
                    text = "Habit name",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            shape = RoundedCornerShape(40),
            singleLine = true
        )
        OutlinedTextField(
            value = timeInput,
            onValueChange = {
                timeInput = it
                isValidTime = it.matches(Regex("^([01]\\d|2[0-3]):([0-5]\\d)$"))
                if (isValidTime) {
                    onHabitTimeChange(it)
                }
            },
            label = {
                Text(
                    "Enter Time (HH:mm)",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                )
            },
            isError = !isValidTime,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            shape = RoundedCornerShape(40),
            singleLine = true,
        )
        if (!isValidTime) {
            Text(
                text = "Invalid time format. Please use HH:mm.",
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


