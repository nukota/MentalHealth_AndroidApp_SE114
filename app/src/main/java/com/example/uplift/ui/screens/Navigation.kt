package com.example.uplift.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uplift.R
import com.example.uplift.ui.theme.*
import androidx.compose.ui.zIndex
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationBar(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    var selectedButton by remember { mutableStateOf(currentRoute ?: Routes.HOME ) }

    LaunchedEffect(currentRoute) {
        if (currentRoute != null) {
            selectedButton = currentRoute
        }
    }

    Box(
        modifier = Modifier
            .background(White)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(0.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(0.3f))
            NavigationButton(
                "Home",
                painterResource(id = R.drawable.home),
                isSelected = selectedButton == Routes.HOME,
                onClick = {
                    navController.navigate(Routes.HOME)
                    selectedButton = Routes.HOME
                },
                modifier = Modifier.weight(1f)
            )
            NavigationButton(
                "Habit",
                painterResource(id = R.drawable.medal),
                isSelected = selectedButton == Routes.HABIT,
                onClick = {
                    navController.navigate(Routes.HABIT)
                    selectedButton = Routes.HABIT
                },
                modifier = Modifier.weight(1f)
            )
            NavigationButton(
                "Diary",
                painterResource(id = R.drawable.book),
                isSelected = selectedButton == Routes.DIARY,
                onClick = {
                    navController.navigate(Routes.DIARY)
                    selectedButton = Routes.DIARY
                },
                modifier = Modifier.weight(1f)
            )
            NavigationButton(
                "Explore",
                painterResource(id = R.drawable.compass),
                isSelected = selectedButton in listOf(
                    Routes.EXPLORE,
                    Routes.LIST_TESTS,
                    Routes.STORY,
                    Routes.LIST_SPECIALIST
                ),
                onClick = {
                    navController.navigate(Routes.EXPLORE)
                    selectedButton = Routes.EXPLORE
                },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.weight(0.3f))
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Cyan)
            .zIndex(2f)
    )
}

@SuppressLint("RememberReturnType")
@Composable
fun NavigationButton(
    s: String,
    icon: Painter,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) Color(0x33007178) else Color.Transparent
    val itemColor = if (isSelected) Color(0xff00A0AA) else Gray

    Box(
        modifier = modifier
            .size(width = 60.dp, height = 56.dp)
            .clickable(onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(60.dp, 36.dp)
                    .background(backgroundColor, RoundedCornerShape(18.dp))
            ) {
                Image(
                    painter = icon,
                    colorFilter = ColorFilter.tint(itemColor),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                s,
                color = itemColor,
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium))
                )
            )
        }
    }
}

@Preview
@Composable
fun NavigationButtonPreview() {
    NavigationButton("Home", painterResource(id = R.drawable.home), true, {})
}