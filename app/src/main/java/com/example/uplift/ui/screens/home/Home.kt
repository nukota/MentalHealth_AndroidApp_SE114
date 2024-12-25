package com.example.uplift.ui.screens.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.uplift.ui.composables.TopPaddingContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.uplift.R
import com.example.uplift.ui.composables.DateItem
import com.example.uplift.ui.composables.HabitItem
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.theme.White
import com.example.uplift.viewmodels.AuthViewModel
import com.example.uplift.viewmodels.HabitViewModel
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController, authViewModel: AuthViewModel, habitViewModel: HabitViewModel) {
    var selectedDate by remember { mutableStateOf(15) } // Set the selected date to today of the month
    val dates = remember { generateDates(selectedDate) }
    val habits by habitViewModel.habits.observeAsState(emptyList())
    val habitLogs by habitViewModel.habitLogs.observeAsState(emptyList())
    var habitItems: List<HabitViewModel.Quintuple<String, String, Int, String, Int>> by remember { mutableStateOf(emptyList()) }
    val listState = rememberLazyListState()
    var initialScrollPerformed by remember { mutableStateOf(false) }
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM")
    val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid

    LaunchedEffect(habits, habitLogs, selectedDate) {
        if (!initialScrollPerformed) {
            listState.scrollToItem(selectedDate)
            initialScrollPerformed = true
        }
        habitViewModel.getHabits()
        habitViewModel.getHabitLogs()
        if (habits.isNotEmpty() && habitLogs.isNotEmpty()) {
            habitItems = habitViewModel.getHabitLogByDate(dates[selectedDate], currentUserUid)
            Log.d("HabitList", habitItems.toString())
        }
        habits.forEach { habit ->
            val habitLogList = habitViewModel.getHabitLogsForHabit(habit.habit_id)
            habitViewModel.createMissingHabitLogs(habit.habit_id, habit.date_created, habitLogList) { newHabitLog ->
                habitViewModel.insertHabitLog(newHabitLog, {
                    Log.d("HabitLog", "Inserted new habit log")
                }, {
                    Log.d("HabitLog", "Failed to insert new habit log")
                })
            }
        }
    }

    TopPaddingContent {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
            ) {
                Row() {
                    Column(
                    ) {
                        Text(
                            text = "Home",
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
                                .clickable {navController.navigate(Routes.SETTINGS) }
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 30.dp)
                )
                Text(
                    text = if (dates[selectedDate] == LocalDate.now()) "Today" else dates[selectedDate].format(dateFormatter),
                    color = Color.Black,
                    style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 32.dp, start = 36.dp, bottom = 16.dp)
                )
                LazyRow(
                    state = listState,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(dates.size) { index ->
                        DateItem(date = dates[index], isSelected = index == selectedDate, onClick = {
                            selectedDate = index
                            habitItems = habitViewModel.getHabitLogByDate(dates[selectedDate], currentUserUid)
                        })
                    }
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(18.dp),
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .padding(top = 40.dp)
                ) {
                    items(habitItems) { habitItem ->
                        HabitItem(
                            habitName = habitItem.first,
                            time = habitItem.second,
                            status = habitItem.third,
                            category = habitItem.fourth,
                            habitLogId = habitItem.fifth,
                            onStatusClick = { newStatus ->
                                habitViewModel.updateHabitLogStatus(habitItem.fifth, newStatus, {
                                    Log.d("HabitLog", "Updated habit log status")
                                }, {
                                    Log.d("HabitLog", "Failed to update habit log status")
                                })
                            }
                        )
                    }
                }
            }
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
fun generateDates(todayIndex: Int): List<LocalDate> {
    val today = LocalDate.now()
    return List(30) { today.minusDays((todayIndex - it).toLong()) }
}