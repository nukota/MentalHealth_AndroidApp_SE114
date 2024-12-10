package com.example.uplift.ui.screens.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.uplift.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.uplift.ui.composables.TopPaddingContent
import com.example.uplift.viewmodels.DiaryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDiaryScreen(
    diaryId: Int, navController: NavController, diaryViewModel: DiaryViewModel
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

    TopPaddingContent {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row {
                Column(
                ) {
                    Text(
                        text = "Diary",
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
            Column(
                modifier = Modifier
                    .zIndex(1f)
                    .padding(horizontal = 16.dp)
                    .padding(top = 70.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                // Vùng nhập tiêu đề
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    placeholder = {
                        Text(
                            text = "Enter diary title...",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray // Placeholder color
                            )
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black // Text color
                    ),
                    maxLines = 1,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xff007178),
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Black,
                        focusedTextColor = Color.Black,
                        focusedPlaceholderColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                // Vùng nhập nội dung nhật ký
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    placeholder = {
                        Text(
                            text = "Start writing here...",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray // Placeholder color
                            )
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black // Text color
                    ),
                    maxLines = Int.MAX_VALUE,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xff007178),
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Black,
                        focusedTextColor = Color.Black,
                        focusedPlaceholderColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Nút lưu nhật ký
                Button(
                    onClick = {
                        currentDiary?.let { diary ->
                            val updatedDiary = diary.copy(title = title, content = content)
                            diaryViewModel.updateDiary(updatedDiary) {
                                navController.popBackStack() // Quay lại màn hình trước
                            }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 8.dp)
                        .width(120.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff007178),
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Save",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    )
                }
            }
        }
    }
}