package com.example.uplift.ui.screens.diary

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import com.example.uplift.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diary.ui.components.DiaryCard
import com.example.uplift.data.models.Diary
import com.example.uplift.ui.composables.TopPaddingContent
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.theme.White
import com.example.uplift.viewmodels.DiaryViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryScreen(
    navController: NavHostController,
    diaryViewModel: DiaryViewModel = viewModel()
) {
    val diaries by diaryViewModel.diaries.observeAsState(emptyList())
    val searchQuery by diaryViewModel.searchQuery.observeAsState("")

    Log.d("DiaryScreen", "Diaries: ${diaries}")

    LaunchedEffect(Unit) {
        diaryViewModel.getDiaries()  // Gọi getDiaries khi screen được tạo
    }

    TopPaddingContent {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .zIndex(1f)
                    .background(color = Color.White)
            ) {
                Row {
                    Column {
                        Text(
                            text = "Diary",
                            color = Color(0xff101010),
                            style = androidx.compose.ui.text.TextStyle(
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
                                .clickable { navController.navigate(Routes.SETTINGS) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))


                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextField(
                                value = searchQuery,
                                onValueChange = { newQuery ->
                                    diaryViewModel.searchDiaries(newQuery)
                                },
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
                                onClick = { navController.navigate("diary_add") },
                                modifier = Modifier
                                    .size(56.dp)
                                    .padding(start = 4.dp)
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
                    items(diaries) { diary ->
                        Log.d("DiaryScreen", "Displaying diary: $diary")
                        Spacer(modifier = Modifier.height(14.dp))
                        DiaryCard(
                            title = diary.title,
                            dateCreated = diary.date_created,
                            dateModified = diary.date_modified,
                            content = diary.content,
                            onDelete = { diaryViewModel.deleteDiary(diary.diary_id) },
                            onClick = { navController.navigate("diary_update/${diary.diary_id}") }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}