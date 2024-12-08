package com.example.uplift.ui.screens.habit

import android.annotation.SuppressLint
import com.example.uplift.ui.composables.TopPaddingContent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import com.example.uplift.R
import com.example.uplift.data.models.Habit
import com.example.uplift.ui.composables.HabitCard
import com.example.uplift.viewmodels.HabitViewModel
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitScreen(habitViewModel: HabitViewModel) {
    val habits by habitViewModel.habits.observeAsState(initial = emptyList())
    val habitLogs by habitViewModel.habitLogs.observeAsState(initial = emptyList())
    val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid
    val habitList: List<Habit> = habits.filter { it.uid == currentUserUid }

    var isDialogOpen by remember { mutableStateOf(false) }
    if (habits.isEmpty()) {
        // Show loading or empty state
        Spacer(modifier = Modifier.height(20.dp))
        Text("No habits found")
    } else
    TopPaddingContent {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .zIndex(1f)
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

                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 14.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start
                ) {
                    item {
                        Button(
                            onClick = { isDialogOpen = true },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BAC5))
                        ) {
                            Text(
                                text = "Add New",
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                style = TextStyle(fontSize = 18.sp)
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                    }
                    items(habitList) { habit ->
                        HabitCard(
                            habit = habit,
                            habitViewModel.getStatusListForLatestWeek(habitLogs, habit.habit_id)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
            if (isDialogOpen) {
                AddHabitDialog(onDismiss = { isDialogOpen = false })
            }
        }
    }
}
