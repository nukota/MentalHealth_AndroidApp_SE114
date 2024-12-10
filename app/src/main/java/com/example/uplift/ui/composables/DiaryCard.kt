package com.example.diary.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.uplift.R
import com.example.uplift.data.models.Diary
import com.example.uplift.ui.theme.Black
import com.example.uplift.ui.theme.Cyan
import com.example.uplift.ui.theme.White

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DiaryCard(
    title: String,
    dateCreated: String,
    content: String,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() }
            .border(1.dp, Black, RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    modifier = Modifier.width(240.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp),
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Text(
                    text = dateCreated,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 12.sp),
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
                Icon(
                    painter = painterResource(id = R.drawable.close_hb),
                    contentDescription = "Delete Diary",
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.CenterVertically)
                        .clickable(onClick = onDelete)
                        .zIndex(2f),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DiaryList(diaries: List<Diary>, onDiaryClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        diaries.forEach { diary ->
            DiaryCard(
                title = diary.title,
                dateCreated = diary.date_created,
                content = diary.content,
                onClick = { onDiaryClick(diary.diary_id) },
                onDelete = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DiaryCardPreview() {
    DiaryCard(
        title = "My First Diary",
        dateCreated = "2021-10-10",
        content = "Today was a good day. I went for a walk in the park and had a great time. I also met a friend for coffee. It was a great day.",
        onClick = {},
        onDelete = {}
    )
}