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
fun AddDiaryScreen() {
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
                Image(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lời nhắc nhẹ nhàng
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = "Write about yours’ todays",
                    color = Color(0xff007178),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.sailregular)),
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Even in the darkest moments, remember that small steps forward can lead to brighter days.",
                    color = Color(0xff505050),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.sailregular)),
                    lineHeight = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Vùng nhập tiêu đề
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle title input */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        color = Color(0xFFDCFDFF), // Màu nền tùy chỉnh
                        shape = RoundedCornerShape(16.dp) // Bo góc cho toàn bộ vùng nhập
                    ),
                placeholder = { Text("Enter diary title...") },
                maxLines = 1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff007178), // Viền khi focus
                    unfocusedBorderColor = Color.Gray, // Viền khi không focus
                    cursorColor = Color.Black, // Màu con trỏ nhập liệu
                    focusedTextColor = Color.Black, // Màu chữ
                    focusedPlaceholderColor = Color.Gray  // Màu của placeholder
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Vùng nhập nội dung nhật ký
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle input */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Tăng chiều cao để chiếm toàn bộ không gian còn lại
                    .background(
                        color = Color(0xFFDCFDFF), // Nền tùy chỉnh
                        shape = RoundedCornerShape(30.dp) // Bo góc nền
                    ),
                placeholder = { Text("Start writing here...") },
                maxLines = Int.MAX_VALUE, // Cho phép nhập nhiều dòng
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent, // Xóa viền khi focus
                    unfocusedBorderColor = Color.Transparent, // Xóa viền khi không focus
                    cursorColor = Color.Black, // Màu con trỏ nhập liệu
                    focusedTextColor = Color.Black, // Màu chữ
                    focusedPlaceholderColor = Color.Gray // Màu của placeholder
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nút lưu nhật ký
            Button(
                onClick = { /* Handle save action */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
                    .fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff007178), // Màu nền nút
                    contentColor = Color.White // Màu chữ
                ),
                shape = RoundedCornerShape(50) // Bo góc nút
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

@Preview(showBackground = true)
@Composable
fun PreviewAddDiaryScreen() {
    //HabitScreen(rememberNavController(), AuthViewModel())
    AddDiaryScreen()
}
