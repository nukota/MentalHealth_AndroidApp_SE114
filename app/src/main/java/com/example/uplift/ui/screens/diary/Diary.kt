@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.diary.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.uplift.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import com.example.diary.ui.components.DiaryList
import androidx.compose.ui.tooling.preview.Preview
import com.example.uplift.ui.composables.TopPaddingContent
import com.example.uplift.ui.theme.Black
import com.example.uplift.ui.theme.DarkGray
import com.example.uplift.ui.theme.Gray
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.theme.White
import com.example.uplift.viewmodels.DiaryViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DiaryScreen(navController: NavHostController, diaryViewModel: DiaryViewModel) {
    val diaries by diaryViewModel.diaries.observeAsState(initial = emptyList())

    var searchText = remember { mutableStateOf("") }
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 100.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .height(55.dp)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(27.5.dp)
                                )
                                .border(
                                    width = 1.5.dp,
                                    color = Color(0xFF505050),
                                    shape = RoundedCornerShape(27.5.dp)
                                )
                        ) {
                            TextField(
                                leadingIcon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.search),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(DarkGray),
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                                value = searchText.value,
                                placeholder = {
                                    Text(
                                        text = "Search",
                                        color = Gray,
                                        fontSize = 15.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                onValueChange = { searchText.value = it },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    errorContainerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    errorIndicatorColor = Color.Transparent
                                ),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 15.sp,
                                    color = Black,
                                    fontFamily = FontFamily(Font(R.font.interregular)),
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
                                    .height(55.dp)
                            )
                        }
                        Button(
                            onClick = { navController.navigate(Routes.DIARY_ADD_NEW) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                            modifier = Modifier
                                .padding(start = 14.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = "+",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(fontSize = 30.sp)
                            )
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp)
                            .padding(top = 32.dp, bottom = 10.dp)
                    ) {
                        val filteredDiaries = diaries.filter { diary ->
                            diary.title.contains(searchText.value, ignoreCase = true) ||
                                    diary.content.contains(searchText.value, ignoreCase = true)
                        }
                        DiaryList(
                            diaries = filteredDiaries,
                            onDiaryClick = { diaryId ->
                                navController.navigate("diaryDetail/$diaryId")
                            }
                        )
                    }
                }
            }
        }
    }
}
