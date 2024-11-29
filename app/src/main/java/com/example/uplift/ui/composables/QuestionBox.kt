package com.example.uplift.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.uplift.R

@Composable
fun QuestionBox(
    testPurpose: String,
    testName: String,
    testTime: String,
    questionCount:Int,
    iconResId: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .requiredWidth(width = 327.dp)
            .requiredHeight(height = 118.dp)
            .background(color = Color.White)
            .border(
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = rememberAsyncImagePainter(iconResId),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 10.dp, top = 20.dp)
                    .width(50.dp)
                    .height(53.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Text(
                text = testPurpose,
                color = Color(0xFF38AEB4),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Text(
                text = testName,
                color = Color(0xff101010),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                    fontSize = 20.sp
                ),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Row() {
                Column() {
                    Text(
                        text = "$questionCount questions - $testTime minutes",
                        color = Color(0xff999999),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                            fontSize = 13.sp
                        ),
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }
                Column() {
                    Image(
                        painter = painterResource(id = R.drawable.playbutton),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 40.dp)
                            .size(36.dp)
                    )

                }
            }
        }
    }
}