package com.example.uplift.ui.screens.questions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.uplift.data.models.Answer
import com.example.uplift.data.models.Question
import com.example.uplift.ui.composables.AnswerOption
import com.example.uplift.ui.composables.NextPreviousBox
import com.example.uplift.ui.composables.getInitials
import com.example.uplift.ui.theme.Cyan
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.theme.White


@Composable
fun QuestionsScreen(
    navController: NavController,
    questions: List<Question>,
    answers:  List<Answer>,
    currentQuestionIndex: Int,
    onFinish: (testId:Int,testName:String,score:Double) -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    score:Double,
    testId:Int,
    testName:String,
    onScoreUpdated: (Double) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Row(

        ){
            Column(
            ){
                Text(
                    text = getInitials(testName)+ " Test",
                    color = Color(0xff101010),
                    style = TextStyle(fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.lemonada))),
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp)
                        .height(43.dp)
                )

                Text(
                    text = testName,
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
            ){
                Image(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)
                )
            }
        }


        Row(
            horizontalArrangement = Arrangement.Absolute.Right,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 52.dp, top = 52.dp)
                .width(286.dp)
        ) {
            if (currentQuestionIndex >= 1) {
                NextPreviousBox(text = "Previous", onClick = onPrevious)
            }

            Text(
                text = "${currentQuestionIndex + 1}/${questions.size}",
                color = Color(0xff101010),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color(0xff101010),
                    fontFamily = FontFamily(Font(R.font.inter))
                ),
                modifier = Modifier
                    .width(95.dp)
                    .height(24.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            NextPreviousBox(text = "Next", onClick = onNext)
        }

        //questions
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 9.dp)
                .width(500.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 284.dp)
                    .requiredHeight(height = 132.dp)
                    .background(color = Color.White)
                    .border(
                        border = BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(20.dp)
                    )
            )
            {
                Text(
                    text = questions[currentQuestionIndex].question_text.toString(),
                    color = Color(0xff505050),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.sansitadwashedfont))
                    ),
                    modifier = Modifier
                        .padding(horizontal = 9.dp, vertical = 25.dp)
                        .width(265.dp)
                        .height(58.dp)
                )
            }
        }
        //answer
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 30.dp)
                .width(500.dp)
                .height(350.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                val filteredAnswers =
                    answers.filter { it.question_id == questions[currentQuestionIndex].question_order }
                filteredAnswers.forEach { answer ->
                    val iconResId = when (answer.answer_order) {
                        1 -> R.drawable.extremely_happy_1
                        2 -> R.drawable.very_happy_2
                        3 -> R.drawable.generally_3
                        4 -> R.drawable.somtimes_4
                        5->R.drawable.unhappy_5
                        6->R.drawable.dissatisfied_6
                        7->R.drawable.sad_7
                        8->R.drawable.very_sad_8
                        else -> R.drawable.extremely_happy_1
                    }
                    AnswerOption(
                        text = answer.answer_text,
                        iconResId = iconResId,
                        onClick = {
                            onScoreUpdated(score + answer.answer_value)
                        }
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ){
            if (currentQuestionIndex == questions.lastIndex) {
                Button(
                    onClick = { onFinish(testId, testName,score) },
                    colors = ButtonDefaults.buttonColors(containerColor = Cyan),
                    modifier = Modifier
                        .size(width = 120.dp, height = 40.dp)
                ) {
                    Text(
                        text="Finish",
                        style = TextStyle(fontSize = 20.sp, color = White, fontFamily = FontFamily(Font(R.font.interbold)))
                    )
                }
            }
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
                    .clickable { navController.navigate(Routes.LIST_TESTS)}
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
