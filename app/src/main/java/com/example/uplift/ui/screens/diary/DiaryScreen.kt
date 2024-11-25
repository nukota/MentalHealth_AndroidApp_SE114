@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.diary.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.* // Import for state handling
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.uplift.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.tooling.preview.Preview
import com.example.uplift.data.models.Diary
import com.example.uplift.ui.screens.diary.formatDate
import com.example.uplift.ui.viewmodels.AuthViewModel
import com.example.uplift.ui.viewmodels.DiaryModel
@Composable
fun DiaryScreen(viewModel: DiaryModel, navController: NavHostController) {
    // Chuyển LiveData thành State để Compose có thể quan sát
    val diaries by viewModel.getAllDiaries().observeAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            // Tiêu đề và menu
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
                    onClick = { /* Mở menu hoặc xử lý logic */ }
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

            // Danh sách nhật ký
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(diaries) { diary ->
                    DiaryItem(
                        diary = diary,
                        onClick = {
                            // Điều hướng đến màn hình chỉnh sửa nhật ký
                            navController.navigate("edit diary/${diary.diary_id}")
                        },
                        onDelete = {
                            // Xóa nhật ký khi nhấn nút xóa
                            viewModel.deleteDiary(diary)
                        }
                    )
                }
            }
        }

        // Nút thêm nhật ký
        FloatingActionButton(
            onClick = {
                navController.navigate("add diary") // Điều hướng đến AddDiaryScreen
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

@Composable
fun DiaryItem(diary: Diary, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }, // Xử lý sự kiện nhấp vào nhật ký
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = diary.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDelete) {
                    Icon(
                        painter = painterResource(id = R.drawable.close_hb),
                        contentDescription = "Delete Diary",
                        tint = Color.Red
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = diary.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Created: ${formatDate(diary.date_created)}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Text(
                text = "Last Modified: ${formatDate(diary.date_modified)}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}


