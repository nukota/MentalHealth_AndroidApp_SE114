package com.example.uplift.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AddHabitEndDialog(onDismiss: () -> Unit) {
    var selectedOption by remember { mutableStateOf("Every day")}
    var addHabitText by remember { mutableStateOf(false)}

    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .size(width = 320.dp, height = 450.dp)
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Add habit",
                        style = TextStyle(fontSize = 20.sp,
                                fontWeight = FontWeight.Normal),
                            fontFamily = FontFamily(Font(com.example.uplift.R.font.lemonada))
                        )
                    IconButton(
                        onClick = { onDismiss() },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = com.example.uplift.R.drawable.close_hb),
                            contentDescription = "Close",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "How often do you want to do it?",
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                    modifier = Modifier.padding(bottom = 16.dp, top = 15.dp)

                        .padding(bottom = 16.dp, top = 15.dp)
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    RadioButtonOption(
                        text = "Every day",
                        selected = selectedOption == "Every day",
                        onSelect = { selectedOption = "Every day" }
                    )
                    RadioButtonOption(
                        text = "Some days per week",
                        selected = selectedOption == "Some days per week",
                        onSelect = { selectedOption = "Some days per week" }
                    )
                    RadioButtonOption(
                        text = "Some days per month",
                        selected = selectedOption == "Some days per month",
                        onSelect = { selectedOption = "Some days per month" }
                    )
                }

                Spacer(modifier = Modifier.weight(1f)) // Spacer to push content to the bottom

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //Back button
                    Button(
                        onClick = { addHabitText = true },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color(0xFF00B5AD))
                    ) {
                        Text("Back", color = Color(0xFF00B5AD))
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DotIndicator3(isActive = true)
                        DotIndicator3(isActive = true)
                        DotIndicator3(isActive = true)
                    }

                    // Finish Button
                    Button(
                        onClick = {  },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B5AD))
                    ) {
                        Text("Finish", color = Color.White)
                    }
                }


                if (addHabitText) {
                    AddhabitText(onDismiss = { addHabitText = false })
                }
            }
        }
    }
}

@Composable
fun RadioButtonOption(text: String, selected: Boolean, onSelect: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onSelect() }
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF00B5AD))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, fontSize = 16.sp)
    }
}

@Composable
fun DotIndicator3(isActive: Boolean) {
    Box(
        modifier = Modifier
            .size(8.dp)
            .background(
                color = if (isActive) Color(0xFF00B5AD) else Color(0xFFB0BEC5),
                shape = CircleShape
            )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAddHabitEndDialog() {
    AddHabitEndDialog(onDismiss = {})
}
