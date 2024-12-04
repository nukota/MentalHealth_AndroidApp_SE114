package com.example.uplift.ui.screens.habit

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

@Composable
fun AddHabitTab2(habitName: String, habitDescription: String, onHabitNameChange: (String) -> Unit, onHabitDescriptionChange: (String) -> Unit) {

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
            value = habitDescription,
            onValueChange = onHabitDescriptionChange,
            label = {
                Text(
                    "Description (optional)",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            shape = RoundedCornerShape(40),
            singleLine = true,
        )
    }
}


