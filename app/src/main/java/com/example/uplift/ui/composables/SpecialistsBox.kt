package com.example.uplift.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.uplift.R

@Composable
fun SpecialistsBox(
    textName: String,
    textAge: Int,
    iconAvartar: String,
    textProfession: String,
    textYoE: Int,
    textLocation: String,
    textRating: Double,
    textReviewCount: Int,
    onClick: () -> Unit
) {
    val commonTextStyle = TextStyle(
        color = Color(0xFF505050),
        fontFamily = FontFamily(Font(R.font.inter)),
        fontSize = 13.sp
    )

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth(0.88f)
            .requiredHeight(height = 186.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(0xffF8F8F8))
            .clickable(onClick = onClick)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = rememberAsyncImagePainter(iconAvartar),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(12.dp)
                    .size(96.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 14.dp)
        ) {
            Text(
                text = textName,
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
                    text = "$textAge",
                    style = commonTextStyle
                )
            }

            Text(
                text = "Profession: ",
                fontWeight = FontWeight.Bold,
                style = commonTextStyle
            )
            Text(
                text = textProfession,
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
                    text = "$textYoE",
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
                    text = textLocation,
                    color = Color(0xFF505050),
                    style = commonTextStyle
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(17.dp)
                    .padding(end = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxHeight()
                )
                Text(
                    text = "$textRating ($textReviewCount reviews)",
                    color = Color(0xFF787300),
                    textDecoration = TextDecoration.Underline,
                    fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                    fontSize = 12.sp
                )
            }
        }
    }
}