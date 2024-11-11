package com.example.uplift.ui.screens.Specialists

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R
import com.example.uplift.data.models.Specialists
import com.example.uplift.ui.composables.SpecialistsBox
import com.example.uplift.ui.theme.White

@Composable
fun ListSpecialistsScreen (
    listSpecialists: List<Specialists>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .fillMaxWidth()
    ) {
        Row() {
            Column(
            ) {
                Text(
                    text = "Specialists",
                    color = Color(0xff101010),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.lemonada))
                    ),
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp)
                        .height(43.dp)
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
                )
            }
        }

        Spacer(modifier = Modifier.height(38.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(400.dp)
        ) {
            Column(
            ) {

                listSpecialists.forEach { specialist ->
                    val avartar = when (specialist.avartar) {
                        1 -> R.drawable.avartar1
                        2 -> R.drawable.avartar1
                        3 -> R.drawable.avartar1
                        else -> R.drawable.avartar1
                    }

                    SpecialistsBox(
                        iconAvartar = avartar,
                        textName = specialist.full_name,
                        textAge = specialist.age,
                        textProfession = specialist.profession,
                        textYoE = specialist.years_of_experience,
                        textLocation = specialist.location,
                        textRating = specialist.rating,
                        textReviewCount = specialist.review_count,
                        onClick = {
                            // Hành động khi click vào từng câu hỏi
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 20.dp, bottom = 28.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back",
                modifier = Modifier
                    .size(24.dp)
            )

            Text(
                text = "Back",
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
    }

}

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun ListSpecialistsScreenPreview() {
    val sampleSpecialists = listOf(
        Specialists(
            specialist_id = 1,
            full_name = "Dr. Jane Cooper",
            age = 32,
            profession = "Licensed Professional Counselor (Psychotherapist)",
            years_of_experience = 6,
            location = "New York, NY, USA",
            rating = 3.67,
            review_count = 100,
            avartar = 1,
            explore_spec_id = 2
        ),
        Specialists(
            specialist_id = 2,
            full_name = "Dr. Alexander",
            age = 32,
            profession = "Licensed Professional Counselor (Psychotherapist)",
            years_of_experience = 6,
            location = "New York, NY, USA",
            rating = 3.67,
            review_count = 100,
            avartar = 2,
            explore_spec_id = 2
        ),
        Specialists(
            specialist_id = 3,
            full_name = "Dr. Sarah Jones",
            age = 28,
            profession = "Clinical Psychologist",
            years_of_experience = 5,
            location = "Los Angeles, CA, USA",
            rating = 4.5,
            review_count = 200,
            avartar = 3,
            explore_spec_id = 2
        )
    )
    ListSpecialistsScreen(listSpecialists = sampleSpecialists)
}
