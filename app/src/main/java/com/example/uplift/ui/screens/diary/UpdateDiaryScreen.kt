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
import androidx.compose.ui.focus.focusModifier
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
    var title by remember { mutableStateOf("loading ...") }
    var content by remember { mutableStateOf("loading ...") }
    var dateCreated by remember { mutableStateOf("") }
    var dateModified by remember { mutableStateOf("") }
        // Lấy dữ liệu nhật ký khi màn hình được tạo
    LaunchedEffect(diaryId) {
        diaryViewModel.getDiaryById(diaryId)  // Lấy nhật ký từ Firebase
    }
    // Theo dõi dữ liệu nhật ký hiện tại từ ViewModel
    val currentDiary by diaryViewModel.currentDiary.observeAsState()
    // Cập nhật title và content khi có dữ liệu nhật ký
    LaunchedEffect(currentDiary) {
        currentDiary?.let {
            title = it.title
            content = it.content
            dateCreated = it.date_created
            dateModified = it.date_modified
        }
    }

    TopPaddingContent {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Diary",
                    color = Color(0xff101010),
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.lemonada))
                    ),
                    modifier = Modifier
                        .height(54.dp)
                )

                Spacer(modifier = Modifier.weight(1f)) // Cung cấp không gian trống giữa tiêu đề và menu

                Image(
                    painter = painterResource(id = R.drawable.setting),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .size(28.dp)
                        .clickable { /* Add menu click action here */ }
                )
            }
            Column(
                modifier = Modifier
                    .zIndex(1f)
                    .padding(horizontal = 20.dp)
                    .padding(top = 70.dp)
                    .fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .padding(horizontal = 10.dp)
                ) {
                    Column {
                        Text(
                            text = "Created: $dateCreated",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.LightGray
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Modified: $dateModified",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.LightGray
                            )
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(start = 8.dp)
                            .clickable(onClick = {
                                currentDiary?.let { diary ->
                                    val updatedDiary = diary.copy(title = title, content = content)
                                    diaryViewModel.updateDiary(updatedDiary) {
                                        navController.popBackStack() // Quay lại màn hình trước
                                    }
                                }
                            })
                    )
                }
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
            }
        }
    }
}