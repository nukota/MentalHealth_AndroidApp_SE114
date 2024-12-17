package com.example.uplift.ui.screens.habit

import com.example.uplift.ui.composables.TopPaddingContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.uplift.R
import com.example.uplift.ui.theme.Gray
import com.example.uplift.ui.theme.White
import com.example.uplift.viewmodels.HabitViewModel

@Composable
fun HabitDetailScreen(habitId: Int, habitViewModel: HabitViewModel = viewModel()) {
    val habit by habitViewModel.getHabitById(habitId).observeAsState()

    habit?.let {
        TopPaddingContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White)
                    .fillMaxWidth()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    item {
                        Row {
                            Column(
                            ) {
                                Text(
                                    text = "Habit",
                                    color = Color(0xff101010),
                                    style = TextStyle(
                                        fontSize = 32.sp,
                                        fontFamily = FontFamily(Font(R.font.lemonada))
                                    ),
                                    modifier = Modifier
                                        .padding(start = 20.dp, top = 10.dp)
                                        .height(54.dp)
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.spacedBy(40.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 28.dp, top = 28.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.menu),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clickable { /* Add menu click action here */ }
                                )
                            }
                        }
                    }
                    item {
                        Text(
                            text = it.habit_name,
                            color = Color(0xff101010),
                            style = TextStyle(
                                fontSize = 28.sp,
                            ),
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .padding(vertical = 20.dp)
                                .height(48.dp)
                        )
                    }
                }
            }
        }
    }
    if (habit == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .fillMaxWidth()
        ) {
            Text(
                text = "Story not found",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium))
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

