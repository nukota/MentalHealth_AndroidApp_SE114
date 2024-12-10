package com.example.uplift.ui.screens.diary.habit

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


import DiaryViewModel
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.uplift.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BadgeDefaults.containerColor
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.uplift.data.models.Diary
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uplift.ui.screens.diary.formatDate
import com.example.uplift.ui.theme.White
import java.time.format.TextStyle

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDiaryPreview() {
    // Giá trị mặc định cho title và content
    var title by remember { mutableStateOf("My First Diary") }
    var content by remember { mutableStateOf("This is the content of my first diary entry.") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .zIndex(1f)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Row for title and save button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Column for title text
                Column(
                    modifier = Modifier.weight(1f)  // Column takes up remaining space
                ) {
                    Text(
                        text = "Write Your Diary",
                        color = Color(0xff101010),
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.lemonada)),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Express your thoughts",
                        color = Color(0xFF0F858F),
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.sailregular))
                        )
                    )
                }

                Button(
                    onClick = {
                        // Logic save, but here we just show a dummy action
                    },
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterVertically)  // Align the button vertically in the row
                        .fillMaxWidth(0.3f),  // Set width for the button
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD3A265),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    // Use Icon for "Save"
                    Icon(
                        imageVector = Icons.Filled.Check, // The checkmark icon
                        contentDescription = "Save",
                        modifier = Modifier.size(24.dp) // Adjust size of the icon
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        color = Color(0xFFDCFDFF),
                        shape = RoundedCornerShape(16.dp)
                    ),
                placeholder = { Text("Enter diary title...") },
                maxLines = 1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff007178),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedPlaceholderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = Color(0xFFDCFDFF),
                        shape = RoundedCornerShape(30.dp)
                    ),
                placeholder = { Text("Start writing here...") },
                maxLines = Int.MAX_VALUE,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedPlaceholderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddDiary() {
    AddDiaryPreview() // Hiển thị giao diện của AddDiary
}
