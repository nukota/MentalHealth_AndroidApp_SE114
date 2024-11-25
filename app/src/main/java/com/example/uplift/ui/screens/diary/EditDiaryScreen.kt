package com.example.uplift.ui.screens.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.* // Import for state handling
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.uplift.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.tooling.preview.Preview
import com.example.uplift.data.models.Diary
import com.example.uplift.ui.viewmodels.AuthViewModel
import com.example.uplift.ui.viewmodels.DiaryModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDiaryScreen(diary_id: Int, navController: NavHostController, viewModel: DiaryModel) {

    val diary = viewModel.getDiaryById(diary_id).observeAsState()

    if (diary.value != null) {
        var title by remember { mutableStateOf(diary.value!!.title) }
        var content by remember { mutableStateOf(diary.value!!.content) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Edit Diary",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Vùng nhập tiêu đề
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

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.updateDiary(
                        diary.value!!.copy(
                            title = title,
                            content = content,
                            date_modified = System.currentTimeMillis()
                        )
                    )
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Save Changes")
            }
        }
    } else {
        Text(text = "Diary not found", style = MaterialTheme.typography.bodyMedium)
    }
}


