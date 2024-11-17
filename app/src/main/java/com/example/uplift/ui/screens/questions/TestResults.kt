package com.example.uplift.ui.screens.Questions

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.uplift.R
import com.example.uplift.data.models.Gadquestions
import com.example.uplift.data.models.Testresults
import com.example.uplift.data.models.Tests
import com.example.uplift.ui.theme.White

@Composable
fun TestResultsScreen (
    testresults: Testresults,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background2),
            contentDescription = null,
            modifier = Modifier
                .size(800.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .zIndex(1f)
        ) {
            Row() {
                Column(
                ) {
                    Text(
                        text = "Result",
                        color = Color(0xff101010),
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontFamily = FontFamily(Font(R.font.lemonada))
                        ),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp)
                            .height(43.dp)
                    )
                    Text(
                        text = "Mental Health Inventory",
                        color = Color(0xff999999),
                        fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                        fontSize = 20.sp,
                        modifier = Modifier
                            .height(29.dp)
                            .padding(start = 20.dp)
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
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xffd7f9fa))
                    .height(85.dp)
                    .width(260.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxHeight()
                        .padding(top = 5.dp)
                ) { val testID: Int = testresults.test_id
//                    val labeltext: String = when (testID) {
//                        1 -> if (score > 80) "You have" else "You might be experiencing"
//                        else -> "You might be experiencing"
//                    }
                    Text(
                        text = "labeltext",
                        color = Color(0xff505050),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.sansitadwashedfont))
                        ),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                    )

                    Text(
                        text = testresults.result_description.toString(),
                        color = Color(0xff007178),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = FontFamily(Font(R.font.sansitadwashedfont))
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val testID: Int = testresults.test_id
                val IconSymbol: Int = when (testID) {
                    1 -> when {
//                        score <= 17 -> R.drawable.high_mental_health
//                        score <= 23 -> R.drawable.moderate_mental_health
                        else -> R.drawable.serious_mental_health
                    }

                    2 -> when {
//                        score <= 4 -> R.drawable.no_depression
//                        score <= 9 -> R.drawable.mild_depression
//                        score <= 14 -> R.drawable.moderate_depression
//                        score <= 19 -> R.drawable.moderate_servere_depression
                        else -> R.drawable.serious_mental_health
                    }

                    3 -> when {
//                        score <= 4 -> R.drawable.no_anxiety
//                        score <= 9 -> R.drawable.mild_anxiety
//                        score <= 14 -> R.drawable.moderate_anxiety
                        else -> R.drawable.severe_anxiety
                    }

                    else -> R.drawable.high_mental_health
                }

                Image(
                    painter = painterResource(id = IconSymbol),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 20.dp)
                )

            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(296.dp)
                    .padding(20.dp)
            ) {
                Text(
                    text = testresults.result_recommendation.toString(),
                    color = Color(0xff007178),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = FontFamily(Font(R.font.sansitadwashedfont))
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color(0xff101010))
                    .height(50.dp)
                    .width(272.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Reach out to Specialists",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.inter))
                    ),
                )

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
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun TestResultsPreview() {
    val score=14;
    val testResult = Testresults(
        result_id = 1,
        test_id = 101,
        0,0,
        result_description = "Sample description.",
        result_recommendation = "Sample recommendation.Sample description.Sample description.Sample description"
    )
    TestResultsScreen(testresults=testResult)
}