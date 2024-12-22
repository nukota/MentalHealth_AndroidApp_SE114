package com.example.uplift.ui.screens.habit

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.uplift.ui.composables.TopPaddingContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.uplift.R
import com.example.uplift.data.models.HabitLog
import com.example.uplift.ui.composables.HabitDateUnit
import com.example.uplift.ui.theme.Gray
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.theme.White
import com.example.uplift.viewmodels.HabitViewModel
import java.time.LocalDate
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun HabitDetailScreen(
    habitId: Int,
    navController: NavController,
    habitViewModel: HabitViewModel = viewModel()
) {
    val habit by habitViewModel.getHabitById(habitId).observeAsState()
    val habits by habitViewModel.habits.observeAsState(emptyList())
    val habitLogs by habitViewModel.habitLogs.observeAsState(emptyList())
    var habitLogList: List<HabitLog> by remember { mutableStateOf(emptyList()) }
    var selectedMonth by remember { mutableStateOf(YearMonth.now()) }

    LaunchedEffect(habits, habitLogs) {
        habitViewModel.getHabits()
        habitViewModel.getHabitLogs()
        habitLogList = habitViewModel.getHabitLogsForHabit(habitId)
    }

    habit?.let {
        TopPaddingContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
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
                                    .padding(top = 10.dp)
                                    .height(54.dp)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.spacedBy(40.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 28.dp, end = 8.dp)
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
                    Text(
                        text = it.habit_name,
                        color = Color.Gray,
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = FontFamily(Font(R.font.lemonada))
                        ),
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .height(48.dp)
                    )
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Gray)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(bottom = 30.dp, top = 10.dp)
                            .height(60.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.left_arrow),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { selectedMonth = selectedMonth.minusMonths(1) },
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = selectedMonth.month.name.lowercase()
                                    .replaceFirstChar { it.uppercase() },
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.intermedium))
                                )
                            )
                            Text(
                                text = selectedMonth.year.toString(),
                                color = Color.Gray,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.right_arrow),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { selectedMonth = selectedMonth.plusMonths(1) },
                            contentScale = ContentScale.Fit
                        )
                    }
                    Calendar(
                        selectedMonth,
                        habitLogList,
                        onStatusClick = { habitLogId, newStatus ->
                            habitViewModel.updateHabitLogStatus(habitLogId, newStatus, {
                                Log.d("HabitLog", "Updated habit log status")
                            }, {
                                Log.d("HabitLog", "Failed to update habit log status")
                            })
                        })
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.attachment_hb),
                            contentDescription = "Attach",
                            modifier = Modifier.size(16.dp),
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Streak: ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                        )
                        Text(
                            text = it.streak.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF00B5AD),
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.check_hb),
                            contentDescription = "Completion",
                            modifier = Modifier.size(16.dp),
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Completion rate: ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                        )
                        Text(
                            text = "${it.completion_rate.toInt()}%",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF00B5AD),
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 28.dp)
                    ) {
                        Text(
                            text = "Date created: ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                        )
                        Text(
                            text = it.date_created,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Gray)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {
                            habitViewModel.deleteHabit(habitId, {
                                navController.navigate(Routes.HABIT) {
                                    popUpTo(Routes.HABIT_DETAIL) { inclusive = true }
                                }
                            }, { error ->
                                Log.d("HabitLog", "Failed to delete habit: $error")
                            })
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp))
                    ) {
                        Text(
                            text = "Delete this habit",
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            style = TextStyle(fontSize = 18.sp)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
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

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(
    yearMonth: YearMonth,
    habitLogList: List<HabitLog>,
    onStatusClick: (Int, Int) -> Unit
) {
    val daysInMonth = yearMonth.lengthOfMonth()
    val firstDayOfMonth = yearMonth.atDay(1).dayOfWeek.value % 7
    val lastDayOfMonth = yearMonth.atEndOfMonth().dayOfWeek.value % 7
    val previousMonth = yearMonth.minusMonths(1)
    val nextMonth = yearMonth.plusMonths(1)
    val daysInPreviousMonth = previousMonth.lengthOfMonth()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffb2edea)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..7) {
                Box (modifier = Modifier.width(32.dp)) {
                    Text(
                        text = when (i) {
                            1 -> "Mon"
                            2 -> "Tue"
                            3 -> "Wed"
                            4 -> "Thu"
                            5 -> "Fri"
                            6 -> "Sat"
                            7 -> "Sun"
                            else -> ""
                        },
                        fontSize = 12.sp,
                        color = Color(0xFF00B5AD),
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color(0xffb2edea)),
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(16.dp),
            columns = GridCells.Fixed(7),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Add empty boxes for days of the week before the first day of the month
            for (i in 0 until firstDayOfMonth) {
                val date = previousMonth.atDay(daysInPreviousMonth - firstDayOfMonth + i + 1)
                item {
                    Box(
                        modifier = Modifier.size(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.LightGray,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            // Add boxes for each day of the month
            for (day in 1..daysInMonth) {
                val date = yearMonth.atDay(day)
                val habitLog = habitLogList.find { it.date == date.toString() }
                item {
                    if (habitLog != null) {
                        HabitDateUnit(
                            date = date,
                            habitLog = habitLog,
                            onStatusClick = onStatusClick
                        )
                    } else {
                        MissingHabitDateUnit(date)
                    }
                }
            }
            for (i in 1..(6 - lastDayOfMonth)) {
                val date = nextMonth.atDay(i)
                item {
                    Box(
                        modifier = Modifier.size(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.LightGray,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MissingHabitDateUnit(date: LocalDate) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(17.dp)
                )
        ) {
            Text(
                text = date.dayOfMonth.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

