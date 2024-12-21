package com.example.uplift.ui.screens.habit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddHabitTab3(selectedFrequency : Int, onFrequencyChange: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(344.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "How often do you want to do it?",
            fontWeight = FontWeight.Medium,
            style = TextStyle(fontSize = 14.sp, color = Color(0xFF00B5AD)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            RadioButtonOption(
                text = "Every day",
                selected = selectedFrequency == 7,
                onSelect = { onFrequencyChange(7) }
            )
            RadioButtonOption(
                text = "Once a week",
                selected = selectedFrequency == 1,
                onSelect = { onFrequencyChange(1) }
            )
            RadioButtonOption(
                text = "Twice a week",
                selected = selectedFrequency == 2,
                onSelect = { onFrequencyChange(2) }
            )
            RadioButtonOption(
                text = "Three times a week",
                selected = selectedFrequency == 3,
                onSelect = { onFrequencyChange(3) }
            )
            RadioButtonOption(
                text = "Four times a week",
                selected = selectedFrequency == 4,
                onSelect = { onFrequencyChange(4) }
            )
            RadioButtonOption(
                text = "Five times a week",
                selected = selectedFrequency == 5,
                onSelect = { onFrequencyChange(5) }
            )
            RadioButtonOption(
                text = "Six times a week",
                selected = selectedFrequency == 6,
                onSelect = { onFrequencyChange(6) }
            )
        }
    }
}


@Composable
fun RadioButtonOption(text: String, selected: Boolean, onSelect: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clickable { onSelect() }
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF00B5AD))
        )
        Text(text, fontSize = 14.sp)
    }
}
