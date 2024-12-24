package com.example.uplift.ui.screens.questions

import com.example.uplift.ui.composables.TopPaddingContent
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.uplift.R
import com.example.uplift.data.models.TestResult
import com.example.uplift.ui.composables.getInitials
import com.example.uplift.ui.theme.Black
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.theme.White
import com.example.uplift.viewmodels.TestResultsViewModel

@Composable
fun TestResultsScreen(
    testId: Int,
    testName: String,
    score: Double,
    navController: NavController,
    testResultsViewModel: TestResultsViewModel
) {
    val testResult = testResultsViewModel.getTestResultByIdAndScore(testId, score).value

    TopPaddingContent {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background2),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .zIndex(1f)
            ) {
                Row {
                    Column(
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                    ) {
                        Text(
                            text = "Result",
                            color = Color(0xff101010),
                            style = TextStyle(
                                fontSize = 26.sp, fontFamily = FontFamily(Font(R.font.lemonada))
                            ),
                            modifier = Modifier
                        )
                        Text(
                            text = testName,
                            color = Color(0xff999999),
                            fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                            fontSize = 16.sp,
                            modifier = Modifier
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
                                .clickable { navController.navigate(Routes.SETTINGS) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(44.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xffd7f9fa))
                        .height(100.dp)
                        .width(280.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxHeight()
                            .padding(top = 5.dp)
                    ) {
                        val labelText: String = when (testId) {
                            1 -> if (score >= 80) "You have" else "You might be experiencing"
                            else -> "You might be experiencing"
                        }
                        Text(
                            text = labelText,
                            color = Color(0xff505050),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.sansitadwashedfont))
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )

                        if (testResult != null) {
                            Text(
                                text = testResult.result_description,
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
                }
                Spacer(modifier = Modifier.height(30.dp))
                if (testResult != null) {
                    Image(
                        painter = rememberAsyncImagePainter(testResult.picture_url),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(0.8f)
                            .heightIn(min = 200.dp, max = 260.dp)
                            .padding(vertical = 10.dp)
                            .border(1.dp, Black, RoundedCornerShape(20.dp))
                            .clip(RoundedCornerShape(20.dp))
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
                    if (testResult != null) {
                        Text(
                            text = testResult.result_recommendation,
                            color = Color(0xff007178),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = FontFamily(Font(R.font.sansitadwashedfont))
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color(0xff101010))
                        .height(50.dp)
                        .width(284.dp)
                        .clickable { navController.navigate(Routes.LIST_SPECIALIST) }
                ) {
                    Text(
                        text = "Reach out to Specialists",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                        ),
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 20.dp, bottom = 80.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "back",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navController.navigate(Routes.LIST_TESTS) }
                    )

                    Text(
                        text = "Back",
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .clickable { navController.navigate(Routes.LIST_TESTS) }
                    )
                }
            }
        }
    }
}

//    if (testResult == null) {
//        Text(text = "Loading...",
//            modifier = Modifier.fillMaxSize()
//        )
//    }


//@Preview(widthDp = 360, heightDp = 800)
//@Composable
//private fun TestResultsPreview() {
//    val score=14;
//    val testResult = Testresults(
//        result_id = 1,
//        test_id = 101,
//        0,0,
//        result_description = "Sample description.",
//        result_recommendation = "Sample recommendation.Sample description.Sample description.Sample description"
//    )
//    TestResultsScreen(testresults=testResult)
//}