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
fun AddHabitDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        var NextOpen by remember { mutableStateOf(false) }
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
                    text = "Select a category for your habit",
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                    modifier = Modifier.padding(bottom = 16.dp)

                        .padding(bottom = 16.dp, top = 15.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        CategoryItem(
                            iconResId = R.drawable.water_hb,
                            label = "Watering plants"
                        )
                        CategoryItem(
                            iconResId = R.drawable.gym_hb,
                            label = "Training"
                        )
                        CategoryItem(
                            iconResId = R.drawable.mark_hb,
                            label = "Work"
                        )

                        CategoryItem(
                            iconResId = R.drawable.medi_hb,
                            label = "Studying"
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        CategoryItem(
                            iconResId = R.drawable.water_hb,
                            label = "Watering plants"
                        )
                        CategoryItem(
                            iconResId = R.drawable.medi_hb,
                            label = "Health"
                        )
                        CategoryItem(
                            iconResId = R.drawable.music_hb,
                            label = "Entertain"
                        )

                        CategoryItem(
                            iconResId = R.drawable.study_hb,
                            label = "Studying"
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DotIndicator(isActive = true)
                        DotIndicator(isActive = false)
                        DotIndicator(isActive = false)
                    }

                    Button(
                        onClick = {NextOpen = true },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color(0xFF00B5AD))
                    ) {
                        Text("Next", color = Color(0xFF00B5AD))
                    }
                }

                if (NextOpen) {
                    AddhabitText(onDismiss = { NextOpen = false })
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

@Composable
fun CategoryItem(iconResId: Int, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddHabitDialog() {
    AddHabitDialog(onDismiss = {})
}
