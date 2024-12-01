package com.example.uplift.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.uplift.R
import com.example.uplift.ui.theme.Black
import com.example.uplift.ui.theme.Gray

@Composable
fun QuestionBox(
    testPurpose: String,
    testName: String,
    questionCount: Int,
    duration: String,
    iconResId: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .requiredWidth(width = 327.dp)
            .requiredHeight(height = 118.dp)
            .border(
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
            .clickable { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .align(Alignment.CenterVertically)
                .width(50.dp)
                .height(50.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(iconResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            Text(
                text = testPurpose,
                color = Color(0xFF38AEB4),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Text(
                text = testName,
                color = Color(0xff101010),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Text(
                    text = "$questionCount questions",
                    color = Gray,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                        fontSize = 10.sp
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Text(
                    text = " | ",
                    color = Gray,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                        fontSize = 10.sp
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Text(
                    text = duration,
                    color = Gray,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                        fontSize = 12.sp
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Image(
                    painter = painterResource(id = R.drawable.playbutton),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 56.dp)
                        .size(36.dp)
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun QuestionBoxPreview() {
//    QuestionBox(
//        testPurpose = "Test Purpose",
//        testName = "Test Name",
//        questionCount = 10,
//        iconResId = "https://www.example.com/image.jpg",
//        onClick = {}
//    )
//}
