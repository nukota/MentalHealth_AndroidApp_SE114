package com.example.uplift.ui.screens.diary

import DiaryViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.uplift.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDiaryScreen(
    diaryId: String,
    diaryViewModel: DiaryViewModel,
    navController: NavHostController
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    // Lấy dữ liệu nhật ký khi màn hình được tạo
    LaunchedEffect(diaryId) {
        diaryViewModel.getDiaryById(diaryId)  // Lấy nhật ký từ Firebase
    }

    // Theo dõi dữ liệu nhật ký hiện tại từ ViewModel
    val currentDiary = diaryViewModel.currentDiary.observeAsState().value

    // Cập nhật title và content khi có dữ liệu nhật ký
    LaunchedEffect(currentDiary) {
        currentDiary?.let {
            title = it.title
            content = it.content
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .zIndex(1f)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Row for title and save button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)  // Column takes up remaining space
                ) {
                    Text(
                        text = "Update your thoughts",
                        color = Color(0xff999999),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.sailregular))
                        )
                    )
                }

                Button(
                    onClick = {
                        // Kiểm tra nếu có nhật ký và thay đổi
                        currentDiary?.let { diary ->
                            val updatedDiary = diary.copy(title = title, content = content)
                            diaryViewModel.updateDiary(updatedDiary) {
                                navController.popBackStack() // Quay lại màn hình trước
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(0.3f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD3A265),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Save",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(color = Color(0xFFDCFDFF), shape = RoundedCornerShape(16.dp)),
                placeholder = { Text("Enter diary title...") },
                maxLines = 1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff007178),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color(0xFFDCFDFF), shape = RoundedCornerShape(30.dp)),
                placeholder = { Text("Start writing here...") },
                maxLines = Int.MAX_VALUE,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )
        }
    }
}

