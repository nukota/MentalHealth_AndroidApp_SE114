package com.example.uplift.ui.screens.Questions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uplift.R
import com.example.uplift.data.models.Tests
import com.example.uplift.ui.composables.QuestionBox
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.theme.White

@Composable
fun ListTests (
    tests: List<Tests>,
    navController: NavController,
    onFinish: (testId: Int, testName: String) -> Unit
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
                        val iconUrl = test.icon_url

                        QuestionBox(
                            testPurpose = test.test_purpose ?: "No purpose specified",
                            testName = test.test_name,
                            questionCount = test.question_count,
                            testTime = "${test.duration_minutes} min",
                            iconResId = iconUrl,
                            onClick = { onFinish(test.test_id,test.test_name) })
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun TestPreview() {
    val navController = rememberNavController()
    val sampleTests = listOf(
        Tests(1, "Mental Health Inventory", "For evaluating general mental health", 12, 10, "", 1),
        Tests(2, "Patient Health Questionnaire", "For screening depression", 9, 10, "", 1),
        Tests(3, "Generalized Anxiety Disorder", "For screening anxiety", 7, 10, "", 1)
    )
    ListTests(tests = sampleTests,navController=navController,  onFinish = { testId, testName ->
        println("Test ID: $testId, Test Name: $testName")
        navController.navigate("test_results/$testId")
    })
}