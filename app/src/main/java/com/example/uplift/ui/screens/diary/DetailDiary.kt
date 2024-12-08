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
import androidx.compose.runtime.getValue
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
import com.example.uplift.ui.theme.Routes
import com.example.uplift.viewmodels.DiaryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailDiaryScreen(
    navController: NavController, diaryViewModel: DiaryViewModel, diaryId: Int
) {
    val diary = diaryViewModel.getDiaryById(diaryId)
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    title = diary.value?.title.toString()
    content = diary.value?.content.toString()

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
            Column (
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
                Spacer(modifier = Modifier.height(24.dp))
                // Vùng nhập tiêu đề
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(
                            color = Color(0xFFDCFDFF),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    placeholder = { Text("Enter diary title...") },
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
                            color = Color(0xFFDCFDFF),
                            shape = RoundedCornerShape(20.dp)
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
                Spacer(modifier = Modifier.height(10.dp))
                // Nút lưu nhật ký
                Button(
                    onClick = {
//                        diaryViewModel.updateDiary(diaryId, title, content)
                        navController.navigate(Routes.DIARY)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                        .fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff007178),
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
}