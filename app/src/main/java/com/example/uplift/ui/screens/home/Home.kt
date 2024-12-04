package com.example.uplift.ui.screens.home

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.uplift.R
import com.example.uplift.ui.theme.White
import com.example.uplift.viewmodels.AuthViewModel

@Composable
fun HomeScreen(navController: NavController, authViewModel: AuthViewModel) {
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
                modifier = Modifier.fillMaxSize(),
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
                            text = "Home",
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
            }
        }
    }

}