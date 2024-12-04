package com.example.uplift.ui.screens.habit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R

@Composable
fun AddHabitTab1(onCategoryClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(344.dp)
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "Select a category for your habit",
            fontWeight = FontWeight.Medium,
            style = TextStyle(fontSize = 14.sp, color = Color(0xFF00B5AD)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(vertical = 20.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(258.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                CategoryItem(
                    iconResId = R.drawable.medical_hb,
                    label = "Health",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.nutrition_hb,
                    label = "Nutrition",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.finance_hb,
                    label = "Finance",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.plants_hb,
                    label = "Plants",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.education_hb,
                    label = "Education",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.meditation_hb,
                    label = "Meditation",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.social_hb,
                    label = "Social",
                    onClick = onCategoryClick
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                CategoryItem(
                    iconResId = R.drawable.outdoor_hb,
                    label = "Outdoor",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.entertainment_hb,
                    label = "Entertainment",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.training_hb,
                    label = "Training",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.work_hb,
                    label = "Work",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.home_hb,
                    label = "Home",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.sport_hb,
                    label = "Sport",
                    onClick = onCategoryClick
                )
                CategoryItem(
                    iconResId = R.drawable.art_hb,
                    label = "Art",
                    onClick = onCategoryClick
                )
            }
        }
    }
}

@Composable
fun CategoryItem(iconResId: Int, label: String, onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .padding(end = 6.dp)
            .clickable { onClick() }
            .background(Color(0xFFF8F8F8), RoundedCornerShape(6.dp))
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(26.dp)
                .padding(start = 4.dp)
        )
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
