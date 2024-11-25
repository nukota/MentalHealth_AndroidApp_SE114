package com.example.uplift.ui.screens.diary

import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.font.FontStyle
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
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
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
import com.example.diary.ui.DiaryScreen
import com.example.uplift.ui.screens.habit.HabitScreen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDiaryScreen(onSave: (String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Nền nhẹ nhàng
    ) {
        Column(
            modifier = Modifier
                .zIndex(1f)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Tiêu đề và menu
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Write Your Diary",
                        color = Color(0xff101010),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.lemonada)),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Express your thoughts",
                        color = Color(0xff999999),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.sailregular))
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Vùng nhập tiêu đề
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        color = Color(0xFFDCFDFF),
                        shape = RoundedCornerShape(16.dp)
                    ),
                placeholder = { Text("Enter diary title...") },
                maxLines = 1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff007178),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedPlaceholderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Vùng nhập nội dung nhật ký
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = Color(0xFFDCFDFF),
                        shape = RoundedCornerShape(30.dp)
                    ),
                placeholder = { Text("Start writing here...") },
                maxLines = Int.MAX_VALUE,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedPlaceholderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nút lưu nhật ký
            Button(
                onClick = { onSave(title, content) }, // Gọi callback onSave
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
                    .fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff007178),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Save Diary",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

