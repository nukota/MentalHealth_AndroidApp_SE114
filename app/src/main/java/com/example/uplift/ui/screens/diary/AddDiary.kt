package com.example.uplift.ui.screens.diary

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.uplift.data.models.Diary
import com.example.uplift.ui.composables.TopPaddingContent
import com.example.uplift.utils.NotificationHelper
import com.example.uplift.viewmodels.DiaryViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDiaryScreen(
    uid: String, navController: NavController, diaryViewModel: DiaryViewModel
) {
    val context = LocalContext.current
    val diaryId =
        navController.currentBackStackEntry?.arguments?.getString("diary_id")?.toIntOrNull()
    val diary by diaryViewModel.currentDiary.observeAsState() // Observe currentDiary
    // Cập nhật UI với thông tin nhật ký (title và content)
    var title by remember { mutableStateOf(diary?.title ?: "") }
    var content by remember { mutableStateOf(diary?.content ?: "") }

    val currentDiary by diaryViewModel.currentDiary.observeAsState()

    // Nếu có diaryId, lấy thông tin nhật ký
    LaunchedEffect(diaryId) {
        if (diaryId != null) {
            diaryViewModel.getDiaryById(diaryId) // Lấy nhật ký theo ID
        }
    }
    currentDiary?.let { diary ->
        title = diary.title
        content = diary.content
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
                    .padding(horizontal = 16.dp)
                    .padding(top = 70.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                // Lời nhắc nhẹ nhàng
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = "Write about yours’ today's",
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
                Spacer(modifier = Modifier.height(10.dp))
                // Vùng nhập tiêu đề
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
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
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray // Placeholder color
                            )
                        )
                    },
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
                Spacer(modifier = Modifier.height(10.dp))
                // Nút lưu nhật ký
                Button(
                    onClick = {
                        diaryViewModel.addDiary(title = title, content = content, uid = uid, context = context, onSuccess = {}, onFailure = {})
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 6.dp)
                        .fillMaxWidth(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Add diary",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}