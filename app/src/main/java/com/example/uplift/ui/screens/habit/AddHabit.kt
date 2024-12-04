package com.example.uplift.ui.screens.habit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.Text
import com.example.uplift.ui.theme.White

@Composable
fun AddHabitDialog(onDismiss: () -> Unit) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    var habitName by remember { mutableStateOf("") }
    var habitDescription by remember { mutableStateOf("") }
    val isNextEnabled = habitName.isNotEmpty() && habitDescription.isNotEmpty()
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .size(width = 330.dp, height = 480.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp)
                .padding(top = 10.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Add habit",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        ),
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
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(344.dp)
                    ) {
                        when (selectedTabIndex) {
                            0 -> AddHabitTab1( onCategoryClick = { selectedTabIndex += 1 })
                            1 -> AddHabitTab2(
                                habitName = habitName,
                                habitDescription = habitDescription,
                                onHabitNameChange = { habitName = it },
                                onHabitDescriptionChange = { habitDescription = it }
                            )
                            2 -> AddHabitTab3()
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(74.dp)
                            .padding(top = 44.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (selectedTabIndex > 0) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(30.dp)
                                    .clickable(onClick = { selectedTabIndex -= 1 })
                                    .border(
                                        BorderStroke(1.dp, Color(0xFF00B5AD)),
                                        shape = RoundedCornerShape(20)
                                    )
                                    .background(Color.White, RoundedCornerShape(20))
                            ) {
                                Text("Previous", color = Color(0xFF00B5AD))
                            }
                        } else {
                            Spacer(modifier = Modifier.size(100.dp))
                        }

                        Row(
                            modifier = Modifier
                                .width(100.dp)
                                .height(30.dp)
                                .padding(horizontal = 28.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DotIndicator(isActive = selectedTabIndex >= 0)
                            Spacer(modifier = Modifier.width(4.dp))
                            DotIndicator(isActive = selectedTabIndex > 0)
                            Spacer(modifier = Modifier.width(4.dp))
                            DotIndicator(isActive = selectedTabIndex > 1)
                        }

                        when (selectedTabIndex) {
                            0 -> {
                                Spacer(modifier = Modifier.size(100.dp))
                            }
                            1 -> {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(30.dp)
                                        .clickable(
                                            onClick = { if (isNextEnabled) selectedTabIndex += 1 },
                                            enabled = isNextEnabled
                                        )
                                        .background(
                                            if (isNextEnabled) Color(0xFF00B5AD) else Color.Gray,
                                            RoundedCornerShape(20)
                                        )
                                ) {
                                    Text("Next", color = White)
                                }
                            }
                            2 -> {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(30.dp)
                                        .clickable(onClick = { onDismiss() })
                                        .border(
                                            BorderStroke(1.dp, Color(0xFF00B5AD)),
                                            shape = RoundedCornerShape(20)
                                        )
                                        .background(Color(0xFF00B5AD), RoundedCornerShape(20))
                                ) {
                                    Text("Finish", color = White)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DotIndicator(isActive: Boolean) {
    Box(
        modifier = Modifier
            .size(8.dp)
            .background(
                color = if (isActive) Color(0xFF00B5AD) else Color(0xFFB0BEC5),
                shape = CircleShape
            )
    )
}