package com.example.uplift.ui.screens

import com.example.uplift.ui.composables.TopPaddingContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uplift.R
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.theme.White

@Composable
fun ExploreScreen(
    navController: NavController
) {
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
            ) {
                Row() {
                    Column(
                    ) {
                        Text(
                            text = "Explore",
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
                                .clickable { navController.navigate(Routes.SETTINGS) }
                        )
                    }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(18.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 14.dp, vertical = 28.dp)
                ) {
                    item { ExploreImageButton(R.drawable.take_tests) { navController.navigate(Routes.LIST_TESTS) } }
                    item { ExploreImageButton(R.drawable.read_story) { navController.navigate(Routes.STORY) } }
                    item { ExploreImageButton(R.drawable.reach_specialists) { navController.navigate(Routes.LIST_SPECIALIST) } }
                }
            }
        }
    }
}

@Composable
fun ExploreImageButton(painter: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = painter),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .aspectRatio(41 / 45f)
            .clickable { onClick() }
    )
}

@Preview(
    name = "Explore Screen Preview",
    showBackground = true,
    backgroundColor = 0xFFFFFF,
    widthDp = 360,
    heightDp = 800
)
@Composable

fun ExploreScreenPreview() {
    val navController = rememberNavController()
    ExploreScreen(navController = navController)
}

