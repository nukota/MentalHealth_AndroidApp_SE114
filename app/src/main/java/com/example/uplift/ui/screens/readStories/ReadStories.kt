package com.example.uplift.ui.screens.readStories

import com.example.uplift.ui.composables.TopPaddingContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.uplift.R
import com.example.uplift.ui.composables.StoryItem
import com.example.uplift.ui.theme.*
import com.example.uplift.viewmodels.StoryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController


@Composable
fun ReadStoriesScreen(
    navController: NavController,
    storyViewModel : StoryViewModel = viewModel()
) {
    val stories = storyViewModel.stories.observeAsState(initial = emptyList())
    TopPaddingContent {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LightGray)
                )
            }
            Column(
                modifier = Modifier.zIndex(1f)
            ) {
                Row {
                    Column(
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                    ) {
                        Text(
                            text = "Explore", color = Gray, style = TextStyle(
                                fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.lemonada))
                            ), modifier = Modifier
                        )
                        Text(
                            text = "Read Stories", color = Color(0xff101010), style = TextStyle(
                                fontSize = 26.sp, fontFamily = FontFamily(Font(R.font.lemonada))
                            ), modifier = Modifier
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

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 14.dp)
                        .padding(top = 28.dp)
                ) {
                    items(stories.value) { story ->
                        StoryItem(
                            story = story,
                            onClick = { navController.navigate("story_detail/${story.story_id}") }
                        )
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewReadStoriesScreen() {
//    ReadStoriesScreen()
//}