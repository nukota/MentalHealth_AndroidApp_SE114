package com.example.uplift.ui

import android.graphics.Color.rgb
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R
import com.example.uplift.data.models.Phqanswers
import com.example.uplift.data.models.Phqquestions
import androidx.compose.ui.res.painterResource
import com.example.uplift.ui.composables.AnswerOption
import com.example.uplift.ui.composables.NextPreviousBox
import java.lang.Integer.parseInt


@Composable
fun PhqQuestionScreen(
    questions: List<Phqquestions>,
    answers:  List<Phqanswers>,
    onAnswerSelected: (Phqanswers) -> Unit,
    currentQuestionIndex: Int,

    ) {
    var score by remember { mutableStateOf(0) }
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
                    text = "PHQ Test",
                    color = Color(0xff101010),
                    style = TextStyle(fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.lemonada))),
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp)
                        .height(43.dp)
                )

                Text(
                    text = "Patient Health Questionnaire",
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
                    .padding(end=28.dp, top=28.dp)
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
            horizontalArrangement = Arrangement.Absolute.Right, // Căn giữa các phần tử trong Row theo chiều ngang
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 52.dp, top = 52.dp)
                .width(286.dp)
        ) {
            if (currentQuestionIndex >= 1) {
                NextPreviousBox(text = "Previous", onClick = { /* Handle Previous */ })
            }

            Text(
                text = "${currentQuestionIndex + 1}/${questions.size}", // Display current question number and total
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
            NextPreviousBox(text = "Next", onClick = { /* Handle Next */ })
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
                    text = questions[currentQuestionIndex].question_text,
                    color = Color(0xff505050),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.sansitadwashedfont))
                    ),
                    modifier = Modifier
                        .offset(x = 9.dp, y = 25.dp)
                        .requiredWidth(width = 265.dp)
                        .requiredHeight(height = 58.dp)
                )
            }
        }
        val filteredAnswers =
            answers.filter { it.question_id == questions[currentQuestionIndex].question_id }
        //answer
        Row(
            horizontalArrangement = Arrangement.Center, // Căn giữa các phần tử trong Row theo chiều ngang
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 30.dp)
                .width(500.dp)
                .height(450.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                filteredAnswers.forEach { answer ->
                    val iconResId = when (answer.answer_order) {
                        1 -> R.drawable.extremely_happy_1
                        2 -> R.drawable.very_happy_2
                        3 -> R.drawable.generally_3
                        4 -> R.drawable.somtimes_4
                        else -> R.drawable.extremely_happy_1
                    }
                    AnswerOption(
                        text = answer.answer_text,
                        iconResId = iconResId,
                        onClick = {
                            score+=answer.answer_value
                            onAnswerSelected(answer)
                        }
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
            )

            Text(
                text = "Back",
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PhqQuestionScreenPreview() {
    val questions = listOf(
        Phqquestions(1, 2, "Little interest or pleasure in doing things?", 1),
        Phqquestions(2, 2, "Feeling down, depressed, or hopeless?", 2),
        Phqquestions(3, 2, "Trouble falling or staying asleep, or sleeping too much?", 3)
    )

    val answers = listOf(
        Phqanswers(1, 1, "Not at all", 0, 1),
        Phqanswers(2, 1, "Several days", 1, 2),
        Phqanswers(3, 1, "More than half the days", 2, 3),
        Phqanswers(4, 1, "Nearly every day", 3, 4),

        Phqanswers(5, 2, "Not at all", 0, 1),
        Phqanswers(6, 2, "Several days", 1, 2),
        Phqanswers(7, 2, "More than half the days", 2, 3),
        Phqanswers(8, 2, "Nearly every day", 3, 4),

        Phqanswers(9, 3, "Not at all", 0, 1),
        Phqanswers(10, 3, "Several days", 1, 2),
        Phqanswers(11, 3, "More than half the days", 2, 3),
        Phqanswers(12, 3, "Nearly every day", 3, 4),
    )

    val onAnswerSelected: (Phqanswers) -> Unit = { answer ->
    }

    val currentQuestionIndex = 0

    PhqQuestionScreen(
        questions = questions,
        answers = answers,
        onAnswerSelected = onAnswerSelected,
        currentQuestionIndex = currentQuestionIndex
    )
}
