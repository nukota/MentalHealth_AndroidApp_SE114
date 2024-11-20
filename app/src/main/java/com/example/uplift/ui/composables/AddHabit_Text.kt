package com.example.uplift.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.uplift.R

@Composable
fun AddhabitText(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        var addHabit by remember { mutableStateOf(false) }
        var addHabitEnd by remember { mutableStateOf(false)}

        Box(
            modifier = Modifier
                .size(width = 320.dp, height = 450.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Add habit",
                        style = TextStyle(fontSize = 20.sp,
                            fontWeight = FontWeight.Normal),
                        fontFamily = FontFamily(Font(R.font.lemonada))
                    )
                    IconButton(
                        onClick = { onDismiss() },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close_hb),
                            contentDescription = "Close",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Text(
                    text = "Defind your habit",
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                    modifier = Modifier.padding(bottom = 16.dp)

                        .padding(bottom = 16.dp, top = 15.dp)
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = {    Text(
                        text = "Habit name",
                        style = TextStyle(
                            fontSize = 15.sp,
                            //fontFamily = CustomFontFamily,
                            color = Color.Gray
                        )
                    )  },
                    modifier = Modifier
                        .fillMaxWidth()
                       // .heightIn(min = 40.dp, max = 50.dp)
                        .padding(top = 25.dp),
                    shape = RoundedCornerShape(60),
                    singleLine = true
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(
                        "Description (optional)",
                        style = TextStyle(
                        fontSize = 15.sp,
                        //fontFamily = CustomFontFamily,
                        color = Color.Gray
                    )) },
                    modifier = Modifier
                        .fillMaxWidth()
                        //.heightIn(min = 40.dp, max = 50.dp)
                        .padding(bottom = 25.dp),
                    shape = RoundedCornerShape(50),
                    singleLine = true
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back Button
                    Button(
                        onClick = { addHabit = true },
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
                        DotIndicator2(isActive = true)
                        DotIndicator2(isActive = true)
                        DotIndicator2(isActive = false)
                    }

                    Button(
                        onClick = {  addHabitEnd = true },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color(0xFF00B5AD))
                    ) {
                        Text("Next", color = Color(0xFF00B5AD))
                    }

                    if (addHabitEnd){
                        AddHabitEndDialog(onDismiss = { addHabitEnd = false })
                    }

                    if (addHabit) {
                        AddHabitDialog(onDismiss = { addHabit = false })
                    }
                }

            }
        }
    }
}

@Composable
fun DotIndicator2(isActive: Boolean) {
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
fun PreviewAddHabitDialog2() {
    AddhabitText(onDismiss = {})
}
