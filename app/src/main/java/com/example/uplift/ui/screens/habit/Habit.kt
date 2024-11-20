package com.example.uplift.ui.screens.habit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.* // Import for state handling
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.uplift.R
import com.example.uplift.ui.composables.HabitCard
import com.example.uplift.ui.composables.AddHabitDialog
import com.example.uplift.ui.theme.White
import com.example.uplift.ui.viewmodels.AuthViewModel

@Composable
fun HabitScreen() {
    var isDialogOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(horizontal = 16.dp)
                .zIndex(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Habits",
                    color = Color(0xFF101010),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.lemonada)),
                        fontWeight = FontWeight.Bold
                    )
                )

                // Menu Icon
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_hb),
                        contentDescription = "Menu",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { isDialogOpen = true },
                modifier = Modifier.padding(bottom = 5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BAC5))
            ) {
                Text(
                    text = "Add New",
                    color = Color.Black,
                    style = TextStyle(fontSize = 20.sp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Habit Cards
            HabitCard(title = "Meditation", time = "Everyday at 7:30 a.m", isComplete = true)
            Spacer(modifier = Modifier.height(16.dp))
            HabitCard(title = "Water your plant", time = "Everyday at 7:30 a.m", isComplete = false)
            Spacer(modifier = Modifier.height(16.dp))
            HabitCard(title = "Do your homework", time = "At anytime", isComplete = true)
            Spacer(modifier = Modifier.height(16.dp))
            HabitCard(title = "Meeting", time = "Today at 20:00 p.m", isComplete = true)
        }

        if (isDialogOpen) {
            AddHabitDialog(onDismiss = { isDialogOpen = false })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHabitScreen() {
    //HabitScreen(rememberNavController(), AuthViewModel())
    HabitScreen()
}
