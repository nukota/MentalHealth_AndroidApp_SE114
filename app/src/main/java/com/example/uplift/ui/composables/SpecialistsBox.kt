package com.example.uplift.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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
            .requiredHeight(height = 200.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color(0xffF8F8F8))
            .clickable(onClick = onClick)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 18.dp, vertical = 10.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(120.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(32.dp))
                    .background(color = Color(0xffF8F8F8))
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(iconAvartar),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }

        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 28.dp, bottom = 14.dp)
        ) {
            Text(
                text = textName,
                color = Color(0xFF007178),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                ),
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Text(
                    text = "Age: ",
                    fontWeight = FontWeight.SemiBold,
                    style = commonTextStyle
                )
                Text(
                    text = "$textAge",
                    style = commonTextStyle
                )
            }
            Row {
            Text(
                text = "Profession: ",
                fontWeight = FontWeight.SemiBold,
                style = commonTextStyle
            )
            Text(
                text = textProfession,
                style = commonTextStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )}
            Row(
            ) {
                Text(
                    text = "Year of Experience: ",
                    fontWeight = FontWeight.SemiBold,
                    style = commonTextStyle
                )
                Text(
                    text = "$textYoE",
                    style = commonTextStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
            ) {
                Text(
                    text = "Location: ",
                    fontWeight = FontWeight.SemiBold,
                    style = commonTextStyle
                )
                Text(
                    text = textLocation,
                    color = Color(0xFF505050),
                    style = commonTextStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
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
                        .size(14.dp)
                )
                Text(
                    text = "$textRating ($textReviewCount reviews)",
                    color = Color(0xFF787300),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SpecialistsBoxPreview() {
    SpecialistsBox(
        textName = "Jane Doe",
        textAge = 32,
        iconAvartar = "https://example.com/avatar.jpg",
        textProfession = "Psychologist",
        textYoE = 8,
        textLocation = "New York",
        textRating = 4.8,
        textReviewCount = 128,
        onClick = {}
    )
}