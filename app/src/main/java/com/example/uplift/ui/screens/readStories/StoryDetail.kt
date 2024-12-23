package com.example.uplift.ui.screens.readStories

import com.example.uplift.ui.composables.TopPaddingContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.uplift.R
import com.example.uplift.ui.theme.Gray
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.theme.White
import com.example.uplift.viewmodels.StoryViewModel

@Composable
fun StoryDetailScreen(storyId: Int, navController: NavController, storyViewModel: StoryViewModel = viewModel()) {
    val story by storyViewModel.getStoryById(storyId).observeAsState()

    story?.let {
        TopPaddingContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White)
                    .fillMaxWidth()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    item {
                        Row() {
                            Column(
                                modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                            ) {
                                Text(
                                    text = "Explore", color = Gray, style = TextStyle(
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.lemonada))
                                    ), modifier = Modifier
                                )
                                Text(
                                    text = "Read Stories",
                                    color = Color(0xff101010),
                                    style = TextStyle(
                                        fontSize = 26.sp,
                                        fontFamily = FontFamily(Font(R.font.lemonada))
                                    ),
                                    modifier = Modifier
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.spacedBy(40.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 28.dp, top = 28.dp)
                            ) {
                                Image(painter = painterResource(id = R.drawable.menu),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clickable { navController.navigate(Routes.SETTINGS) }
                                )
                            }
                        }
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(Gray)
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .padding(top = 30.dp, bottom = 25.dp, start = 20.dp, end = 20.dp)
                                .fillMaxWidth()
                                .height(160.dp)
                        ) {
                            AsyncImage(
                                model = it.cover,
                                contentDescription = "story book cover",
                                modifier = Modifier
                                    .size(140.dp, 160.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Column(
                                modifier = Modifier.padding(start = 20.dp)
                            ) {
                                Text(
                                    text = it.title,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.intermedium))
                                    ),
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "by " + it.author,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        color = Gray,
                                        fontFamily = FontFamily(Font(R.font.intermedium))
                                    ),
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = it.description,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = Gray,
                                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                        fontFamily = FontFamily(Font(R.font.interregular))
                                    ),
                                )
                            }
                        }
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp)
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(Gray)
                        )
                    }
                    item {
                        Text(
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp, top = 25.dp),
                            text = it.content,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Light,
                            )
                        )
                    }
                }
            }
        }
    }
    if (story == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .fillMaxWidth()
        ) {
            Text(
                text = "Story not found",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium))
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

