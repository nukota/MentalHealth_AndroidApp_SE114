package com.example.uplift.ui.screens.questions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.uplift.R
import com.example.uplift.data.models.Gadquestions
import com.example.uplift.data.models.Tests
import com.example.uplift.ui.theme.White

@Composable
fun ListTests (
    tests: List<Tests>,
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
                        text = "Explore",
                        color = Color(0xff999999),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.lemonada))
                        ),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp)
                            .height(43.dp)
                    )


                    Text(
                        text = "Take a Test",
                        color = Color(0xff101010),
                        fontFamily = FontFamily(Font(R.font.lemonada)),
                        fontSize = 28.sp,
                        modifier = Modifier
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

            Text(
                text = "Dear User,",
                color = Color(0xff007178),
                fontFamily = FontFamily(Font(R.font.sailregular)),
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 20.dp, top = 27.dp)
            )

            Text(
                text = "You don't seem to be feeling your best today. \n" +
                        "Take a moment to complete the test below and check in on your mental health.",
                color = Color(0xff505050),
                fontFamily = FontFamily(Font(R.font.sailregular)),
                fontSize = 14.sp,
                modifier = Modifier
                    .width(268.dp)
                    .height(76.dp)
                    .padding(start = 20.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center, // Căn giữa các phần tử trong Row theo chiều ngang
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(500.dp)
                    .height(450.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {

                    tests.forEach { test ->
                        val iconResId = when (test.test_id) {
                            1 -> R.drawable.mhi
                            2 -> R.drawable.phq
                            3 -> R.drawable.gad
                            else -> R.drawable.mhi
                        }

                        QuestionBox(
                            testPurpose = test.test_purpose ?: "No purpose specified",
                            testName = test.test_name,
                            questionCount = test.question_count,
                            testTime = "${test.duration_minutes} min",
                            iconResId = iconResId,
                            onClick = {
                                // Hành động khi click vào từng câu hỏi
                            }
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun QuestionBox(
    testPurpose: String,
    testName: String,
    testTime: String,
    questionCount:Int,
    iconResId: Int,
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
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = iconResId),
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
                fontWeight = FontWeight.Medium,
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
                fontWeight = FontWeight.Medium,
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
                        fontWeight = FontWeight.Medium,
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

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun TestPreview() {
    val sampleTests = listOf(
        Tests(1, "Mental Health Inventory", "For evaluating general mental health", 12, 10, "", 1),
        Tests(2, "Patient Health Questionnaire", "For screening depression", 9, 10, "", 1),
        Tests(3, "Generalized Anxiety Disorder", "For screening anxiety", 7, 10, "", 1)
    )
    ListTests(tests = sampleTests)
}