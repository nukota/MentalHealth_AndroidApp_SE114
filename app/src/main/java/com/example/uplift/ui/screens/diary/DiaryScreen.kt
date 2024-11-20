@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.diary.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.* // Import for state handling
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.uplift.R
import com.example.uplift.ui.composables.HabitCard
import com.example.uplift.ui.composables.AddHabitDialog
import com.example.uplift.ui.theme.White
import com.example.uplift.ui.viewmodels.AuthViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.diary.ui.components.DiaryList

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.uplift.ui.screens.habit.HabitScreen
@Composable
fun DiaryScreen(navController: NavHostController) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) } // State cho thanh tìm kiếm

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Toàn bộ nền là màu trắng
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White) // Nền của cột
                .padding(horizontal = 16.dp)
                .zIndex(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Diary",
                    color = Color(0xFF101010),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.lemonada)),
                        fontWeight = FontWeight.Bold
                    )
                )
                IconButton(
                    onClick = { /* Handle menu click */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_hb),
                        contentDescription = "Menu",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text("Search for diary") },
                singleLine = true,
                shape = RoundedCornerShape(30.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                val sampleDiaries = listOf(
                    "Friday 3rd March" to "Dear diary, In March, me and Mole decided to go out swimming. We both arrived together...",
                    "Friday 3rd March" to "Dear diary, A new day, its snowy outside and Mole is here. In a few days it will be sunny again...",
                    "Saturday 4th March" to "2017 Dear Diary, Today me and mole got terrified by the weasels because every step I took they made a click..."
                )

                val filteredDiaries = sampleDiaries.filter { diary ->
                    diary.first.contains(searchText.text, ignoreCase = true) ||
                            diary.second.contains(searchText.text, ignoreCase = true)
                }

                DiaryList(
                    diaries = filteredDiaries,
                    onDiaryClick = { diaryDate ->
                        // Handle diary click
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = {
                navController.navigate("add_diary_screen") // Điều hướng đến AddDiaryScreen
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(72.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_hb),
                contentDescription = "Add Diary",
                modifier = Modifier.size(70.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiaryScreen() {
    DiaryScreen(navController = rememberNavController())
}
