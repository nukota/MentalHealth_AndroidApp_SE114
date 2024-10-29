package com.example.uplift.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R
import com.example.uplift.ui.composables.*
import com.example.uplift.ui.theme.Cyan

@Composable
fun NavigationBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(0.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationButton("Home", painterResource(id = R.drawable.home))
            NavigationButton("Habit", painterResource(id = R.drawable.medal))
            NavigationButton("Diary", painterResource(id = R.drawable.book))
            NavigationButton("Explore", painterResource(id = R.drawable.compass))
        }
    }
}

@Composable
fun NavigationButton(s: String, icon: Painter) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = Modifier
            .size(width = 80.dp, height = 100.dp)) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun NavigationBarPreview() {
    NavigationBar()
}