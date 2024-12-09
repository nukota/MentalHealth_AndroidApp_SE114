package com.example.diary.ui

import DiaryViewModel
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.uplift.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.uplift.data.models.Diary
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uplift.ui.screens.diary.formatDate
import com.example.uplift.ui.theme.White
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryScreen(
    navController: NavHostController,
    diaryViewModel: DiaryViewModel
) {
    // Quan sát LiveData từ ViewModel
    val diaries by diaryViewModel.diaries.observeAsState(emptyList())

    val searchQuery by diaryViewModel.searchQuery.observeAsState("")

    Log.d("DiaryScreen", "Diaries: ${diaries}")

    LaunchedEffect(Unit) {
        diaryViewModel.getDiaries()  // Gọi getDiaries khi screen được tạo
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .zIndex(1f)
        ) {
            // Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 40.dp)
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
                        .padding(end = 20.dp, top = 18.dp)
                        .size(28.dp)
                        .clickable { /* Add menu click action here */ }
                )
            }

            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { newQuery ->
                        diaryViewModel.searchDiaries(newQuery) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .width(400.dp)
                        .height(50.dp)
                        .border(2.dp, Color.Gray, RoundedCornerShape(30.dp))
                        .padding(start = 12.dp, end = 10.dp),
                    placeholder = {
                        Text(
                            text = "Search...",
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp,
                                color = Color.Gray
                            )
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent, // Loại bỏ viền dưới khi focus
                        unfocusedIndicatorColor = Color.Transparent // Loại bỏ viền dưới khi không focus
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                IconButton(
                    onClick = { navController.navigate("add_diary") },
                    modifier = Modifier
                        .size(70.dp)
                        .padding(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_hb),
                        contentDescription = "Add Diary",
                        modifier = Modifier.size(70.dp),
                        tint = Color.Unspecified
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(diaries) { diary ->
                    Log.d("DiaryScreen", "Displaying diary: $diary")
                            DiaryItem(
                                diary = diary,
                                onClick = {
                                    navController.navigate("update_diary/${diary.diary_id}")
                                },
                                onDelete = {
                                    diaryViewModel.deleteDiary(diary.diary_id)
                                }
                            )
                        }
                    }
            }

        }
    }

@Composable
fun DiaryItem(diary: Diary, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()

            .padding(vertical = 7.dp, horizontal = 15.dp)
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(25.dp) // Viền màu đen

    ) {
        Column(
            modifier = Modifier.
            height(100.dp)
                .padding(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .height(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = diary.title,
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(onClick = onDelete) {
                    Icon(
                        painter = painterResource(id = R.drawable.close_hb),
                        contentDescription = "Delete Diary",
                        modifier = Modifier.size(15.dp),
                        tint = Color.Black
                    )
                }
            }
            Column(
                modifier = Modifier.height(70.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp))
            {
                Text(
                    text = diary.content,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(7.dp) )
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
}
