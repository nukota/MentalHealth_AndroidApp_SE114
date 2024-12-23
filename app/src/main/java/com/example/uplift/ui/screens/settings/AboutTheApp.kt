package com.example.uplift.ui.screens.settings

import ButtonSettings
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uplift.R
import com.example.uplift.data.models.User
import com.example.uplift.ui.composables.QuestionBox
import com.example.uplift.ui.composables.SettingsBox
import com.example.uplift.ui.composables.TopPaddingContent
import com.example.uplift.ui.theme.White

@Composable
fun AboutApp (
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
                    .fillMaxSize()

            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "About The App",
                        color = Color(0xff101010),
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontFamily = FontFamily(Font(R.font.lemonada))
                        ),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 10.dp, bottom = 16.dp)
                            .height(54.dp)
                    )
                }
                Spacer(modifier = Modifier.height(65.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 233.dp, height = 131.dp)
                    )
                }

                Spacer(modifier = Modifier.height(65.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .requiredHeight(height = 30.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Version 1.1.0",
                        color = Color(0xFF000000),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                            fontSize = 20.sp
                        ),
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .requiredHeight(height = 30.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "@2024-2026",
                        color = Color(0xFF999999),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                            fontStyle = FontStyle.Italic,
                            fontSize = 15.sp
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(85.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ButtonSettings(text = "Licences", width = 123,modifier=Modifier.background(Color.Gray)) { }
                }

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 20.dp, bottom = 28.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable() {
                                navController.popBackStack()
                            })

                    Text(text = "Back",
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .clickable() {
                                navController.popBackStack()
                            })
                }
            }
        }
    }

}
@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun AboutAppPreview() {
    val navController = rememberNavController()
    AboutApp(navController = navController)
}