package com.example.uplift.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uplift.R
import com.example.uplift.ui.theme.*
import androidx.compose.ui.zIndex

@Composable
fun NavigationBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Cyan)
            .zIndex(2f)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(White)
            .padding(0.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                NavigationButton(
                    "Home",
                    painterResource(id = R.drawable.home),
                    onClick = { /*navController.navigate(Routes.HOME)*/ })
                NavigationButton(
                    "Habit",
                    painterResource(id = R.drawable.medal),
                    onClick = { navController.navigate(Routes.HABIT) })
                NavigationButton(
                    "Diary",
                    painterResource(id = R.drawable.book),
                    onClick = { /*navController.navigate(Routes.DIARY)*/ })
                NavigationButton(
                    "Explore",
                    painterResource(id = R.drawable.compass),
                    onClick = { navController.navigate(Routes.EXPLORE) })
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}

@Composable
fun NavigationButton(s: String, icon: Painter, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = Modifier
            .size(width = 100.dp, height = 80.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = icon,
                colorFilter = ColorFilter.tint(Gray),
                contentDescription = null,
                modifier = Modifier.size(22.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                s,
                color = Gray,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium))
                )
            )
        }
    }
}
