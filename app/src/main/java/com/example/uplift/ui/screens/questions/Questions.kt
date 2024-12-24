package com.example.uplift.ui.screens.questions

import android.widget.Toast
import com.example.uplift.ui.composables.TopPaddingContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uplift.R
import com.example.uplift.data.models.Answer
import com.example.uplift.data.models.Question
import com.example.uplift.ui.composables.AnswerOption
import com.example.uplift.ui.composables.NextPreviousBox
import com.example.uplift.ui.composables.getInitials
import com.example.uplift.ui.theme.Cyan
import com.example.uplift.ui.theme.Gray
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.theme.White
import com.example.uplift.viewmodels.QuestionsViewModel


@Composable
fun QuestionsScreen(
    testId: Int,
    testName: String,
    navController: NavController,
    questionsViewModel: QuestionsViewModel
) {
    val context = LocalContext.current
    LaunchedEffect(testId) {
        questionsViewModel.startNewTest(testId)
    }
    val questions by questionsViewModel.questions.observeAsState(initial = emptyList())
    val answers by questionsViewModel.answers.observeAsState(initial = emptyList())
    val currentQuestionIndex by questionsViewModel.currentQuestionIndex.observeAsState(0)
    val score by questionsViewModel.score.observeAsState(0.0)
    val listQuestion: List<Question> = questions.filter { it.test_id == testId }
    val listAnswer: List<Answer> = answers.filter { it.test_id == testId }
    val selectedAnswerIds = remember { mutableStateOf<Map<Int, Int?>>(emptyMap()) }

    TopPaddingContent {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(White)
        ) {
            Row(
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                ) {
                    Text(
                        text = getInitials(testName) + " Test",
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
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 36.dp)
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                if (currentQuestionIndex >= 1) {
                    NextPreviousBox(
                        modifier = Modifier,
                        text = "Previous",
                        onClick = { questionsViewModel.moveToPreviousQuestion() },
                    )
                } else {
                    Spacer(
                        modifier = Modifier
                            .width(96.dp)
                            .height(29.dp)
                    )
                }
                Text(
                    text = "${currentQuestionIndex + 1}/${listQuestion.size}",
                    color = Color(0xff101010),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = Color(0xff101010),
                        fontFamily = FontFamily(Font(R.font.inter))
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                )
                if (currentQuestionIndex < listQuestion.lastIndex) {
                    NextPreviousBox(
                        modifier = Modifier,
                        text = "Next",
                        onClick = { questionsViewModel.moveToNextQuestion() })
                } else {
                    Spacer(
                        modifier = Modifier
                            .width(96.dp)
                            .height(29.dp)
                    )
                }
            }

            //questions
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 9.dp)
                    .fillMaxWidth(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .requiredHeight(height = 132.dp)
                        .border(
                            border = BorderStroke(2.dp, Color.Black),
                            shape = RoundedCornerShape(20.dp)
                        )
                )
                {
                    Text(
                        text = if (listQuestion.isNotEmpty() && currentQuestionIndex < listQuestion.size) {
                            listQuestion[currentQuestionIndex].question_text
                        } else {
                            "No More Question available"
                        },
                        color = Color(0xff505050),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.sansitadwashedfont))
                        ),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()
                            .padding(vertical = 12.dp, horizontal = 8.dp)
                            .wrapContentHeight(),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            //answer
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp)
                    .fillMaxWidth(0.8f)
                    .height(380.dp)
            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    val filteredAnswers = if (listQuestion.isNotEmpty() && listAnswer.isNotEmpty() && currentQuestionIndex < listQuestion.size) {
                        listAnswer.filter { it.question_id == listQuestion[currentQuestionIndex].question_order }
                    } else {
                        emptyList()
                    }

                    items(filteredAnswers) { answer ->
                        val iconResId = when (answer.answer_order) {
                            1 -> R.drawable.extremely_happy_1
                            2 -> R.drawable.very_happy_2
                            3 -> R.drawable.generally_3
                            4 -> R.drawable.somtimes_4
                            5 -> R.drawable.unhappy_5
                            6 -> R.drawable.dissatisfied_6
                            7 -> R.drawable.sad_7
                            8 -> R.drawable.very_sad_8
                            else -> R.drawable.extremely_happy_1
                        }
                        val selectedAnswerId = selectedAnswerIds.value[answer.question_id]
                        AnswerOption(
                            text = answer.answer_text,
                            iconResId = iconResId,
                            isSelected = selectedAnswerId == answer.answer_order,
                            clickable = {
                                selectedAnswerIds.value = selectedAnswerIds.value.toMutableMap().apply {
                                    put(answer.question_id, answer.answer_order)
                                }
                                questionsViewModel.updateScore(answer.question_id, answer.answer_value, listQuestion.size)
                            }
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(top = 20.dp)
            ) {
                if (currentQuestionIndex == listQuestion.lastIndex && selectedAnswerIds.value.size == listQuestion.size) {
                    val currentScore = questionsViewModel.score.value ?: 0.0
                    Button(
                        onClick = { navController.navigate("test_results/$testId/$testName/$currentScore") },
                        colors = ButtonDefaults.buttonColors(containerColor = Cyan),
                        modifier = Modifier
                            .size(width = 200.dp, height = 40.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "Finish",
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = White,
                                fontFamily = FontFamily(Font(R.font.interregular))
                            ),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                else {
                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Please answer all the questions before finishing.",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Cyan),
                        modifier = Modifier
                            .size(width = 200.dp, height = 40.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "Close",
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = White,
                                fontFamily = FontFamily(Font(R.font.interregular))
                            ),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 20.dp, top = 20.dp)
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
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewGadquestionsScreen() {
//    val navController = rememberNavController()
//    val sampleQuestions = listOf(
//        Questions(1, 1, "How are you?", 1),
//        Gadquestions(2, 1, "How do you feel?", 2)
//    )
//    val sampleAnswers = listOf(
//        Gadanswers(1, 2, "Good", 1, 1),
//        Gadanswers(2, 2, "Bad", 2, 2)
//    )
//
////    GadquestionsScreen(
//        navController = navController,
//        questions = sampleQuestions,
//        answers = sampleAnswers,
//        currentQuestionIndex = 1,
//        score = 0,
//        onNext = {},
//        onPrevious = {},
//        onFinish = ,
//        onScoreUpdated = {}
//    )