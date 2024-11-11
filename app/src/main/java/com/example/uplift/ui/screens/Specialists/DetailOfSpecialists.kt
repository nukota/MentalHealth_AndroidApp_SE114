package com.example.uplift.ui.screens.Specialists

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R
import com.example.uplift.data.models.Specialists
import com.example.uplift.ui.theme.White

@Composable
fun DetailOfSpecialists(
    specialist:Specialists
) {
    val commonTextStyle = TextStyle(
        color = Color(0xFF505050),
        fontFamily = FontFamily(Font(R.font.inter)),
        fontSize = 13.sp
    )
    val avartar = when (specialist.avartar) {
        1 -> R.drawable.avartar1
        2 -> R.drawable.avartar1
        3 -> R.drawable.avartar1
        else -> R.drawable.avartar1
    }
    Column(
        modifier = Modifier
            .requiredWidth(337.dp)
            .requiredHeight(566.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(White)
            .border(border = BorderStroke(2.dp, Color.Black))
    ) {
        Row(
            modifier = Modifier
                .width(337.dp)
                .height(174.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = avartar),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(20.dp)
                        .size(96.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = specialist.full_name,
                    color = Color(0xFF007178),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                        fontSize = 16.sp
                    ),
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                ) {
                    Text(
                        text = "Age: ",
                        fontWeight = FontWeight.Bold,
                        style = commonTextStyle
                    )
                    Text(
                        text = specialist.age.toString(),
                        style = commonTextStyle
                    )
                }

                Text(
                    text = "Profession: ",
                    fontWeight = FontWeight.Bold,
                    style = commonTextStyle
                )
                Text(
                    text = specialist.profession,
                    style = commonTextStyle
                )
                Row(
                ) {
                    Text(
                        text = "Year of Experience: ",
                        fontWeight = FontWeight.Bold,
                        style = commonTextStyle
                    )
                    Text(
                        text = "${specialist.years_of_experience}",
                        style = commonTextStyle
                    )
                }
                Row(
                ) {
                    Text(
                        text = "Location: ",
                        fontWeight = FontWeight.Bold,
                        style = commonTextStyle
                    )
                    Text(
                        text = specialist.location,
                        color = Color(0xFF505050),
                        style = commonTextStyle
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(17.dp)
                        .padding(start = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxHeight()
                    )
                    Text(
                        text = "${specialist.rating} (${specialist.review_count} reviews)",
                        color = Color(0xFF787300),
                        textDecoration = TextDecoration.Underline,
                        fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                        fontSize = 12.sp
                    )
                }
            }
        }
        Column(modifier=Modifier .padding(start=10.dp,top=10.dp) .fillMaxWidth()) {
            Text(
                text = "Specializations: ",
                fontWeight = FontWeight.Bold,
                style = commonTextStyle
            )
            Row(modifier=Modifier .padding(start=10.dp)) {
                Text(
                    text = "• ",
                    style = commonTextStyle,
                    fontSize = 14.sp,
                )
                Text(
                    text = "Cognitive Behavioral Therapy (CBT)",
                    color = Color(0xFF505050),
                    style = commonTextStyle,
                    fontSize = 14.sp,
                )
            }
        }
        Column(modifier=Modifier .padding(start=10.dp,top=20.dp) .fillMaxWidth()) {
            Text(
                text = "Educations: ",
                fontWeight = FontWeight.Bold,
                style = commonTextStyle
            )
            Row(modifier=Modifier .padding(start=10.dp)) {
                Text(
                    text = "• ",
                    style = commonTextStyle,
                    fontSize = 14.sp,
                )
                Text(
                    text = "Cognitive Behavioral Therapy (CBT)",
                    color = Color(0xFF505050),
                    style = commonTextStyle,
                    fontSize = 14.sp,
                )
            }
        }
        Column(modifier=Modifier .padding(start=10.dp,top=20.dp) .fillMaxWidth()) {
            Text(
                text = "Certifications: ",
                fontWeight = FontWeight.Bold,
                style = commonTextStyle
            )
            Row(modifier=Modifier .padding(start=10.dp)) {
                Text(
                    text = "• ",
                    style = commonTextStyle,
                    fontSize = 14.sp,
                )
                Text(
                    text = "Cognitive Behavioral Therapy (CBT)",
                    color = Color(0xFF505050),
                    style = commonTextStyle,
                    fontSize = 14.sp,
                )
            }
        }
        Row( modifier = Modifier .padding(start=140.dp,top=20.dp) .fillMaxWidth()){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color(0xff101010))
                    .height(40.dp)
                    .width(140.dp)
            ) {
                Text(
                    text = "SEND REQUEST",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.inter))
                    ),
                )
            }
        }
    }
}
@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun DetailOfSpecialistsPreview() {
    val specialist = Specialists(
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
        )

    DetailOfSpecialists(specialist = specialist)
}
