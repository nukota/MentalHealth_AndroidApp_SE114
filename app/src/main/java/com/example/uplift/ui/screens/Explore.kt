package com.example.uplift.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.uplift.R
import com.example.uplift.ui.theme.White

@Composable
fun ExploreScreen (
    modifier: Modifier = Modifier
) {
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
                .size(800.dp),
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
                            fontSize = 22.sp,
                            fontFamily = FontFamily(Font(R.font.lemonada))
                        ),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp)
                            .height(43.dp)
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

            Column(
                horizontalAlignment=Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top=30.dp)
                    .fillMaxWidth()
            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.take_tests),
                        contentDescription = null,
                        modifier = Modifier
                            .width(164.dp)
                            .height(180.dp)
                            .padding(10.dp)
                            .clickable { /* Add menu click action here */ }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.read_story),
                        contentDescription = null,
                        modifier = Modifier
                            .width(164.dp)
                            .height(180.dp)
                            .padding(10.dp)
                            .clickable { /* Add menu click action here */ }
                    )
                }
                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.reach_specialists),
                        contentDescription = null,
                        modifier = Modifier
                            .width(164.dp)
                            .height(180.dp)
                            .padding(10.dp)
                            .clickable { /* Add menu click action here */ }
                    )
                }
            }

        }
    }
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
    ExploreScreen()
}

