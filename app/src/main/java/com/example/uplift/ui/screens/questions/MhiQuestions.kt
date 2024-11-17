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
import com.example.uplift.data.models.Mhianswers
import androidx.compose.ui.res.painterResource
import com.example.uplift.data.models.Mhiquestions
import com.example.uplift.ui.composables.AnswerOption
import com.example.uplift.ui.composables.NextPreviousBox
import java.lang.Integer.parseInt


@Composable
fun MhiquestionsScreen(
    questions: List<Mhiquestions>,
    answers:  List<Mhianswers>,
    onAnswerSelected: (Mhianswers) -> Unit,
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
            ) {

                Text(
                    text = "MHI Test",
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
                    text = questions[currentQuestionIndex].question_text.toString(),
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
                        5->R.drawable.unhappy_5
                        6->R.drawable.dissatisfied_6
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
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMhiquestionsScreen() {
    val sampleQuestions = listOf(
        Mhiquestions(1, 1, "How are you?", 1),
        Mhiquestions(2, 1, "How do you feel?", 2),
        Mhiquestions(3, 1, "Not being able to stop or control worrying?", 3),
        Mhiquestions(4, 1, "Trouble relaxing?", 4)
    )
    val sampleAnswers = listOf(
        Mhianswers(1, 1, "Never", 1, 1),
        Mhianswers(2, 1, "Rarely", 2, 2),
        Mhianswers(3, 1, "Sometimes", 3, 3),
        Mhianswers(4, 1, "Often", 4, 4),
        Mhianswers(5, 1, "Always", 5, 5),

        Mhianswers(6, 2, "Never", 1, 1),
        Mhianswers(7, 2, "Rarely", 2, 2),
        Mhianswers(8, 2, "Sometimes", 3, 3),
        Mhianswers(9, 2, "Often", 4, 4),
        Mhianswers(10, 2, "Always", 5, 5),

        Mhianswers(11, 3, "Never", 1, 1),
        Mhianswers(12, 3, "Rarely", 2, 2),
        Mhianswers(13, 3, "Sometimes", 3, 3),
        Mhianswers(14, 3, "Often", 4, 4),
        Mhianswers(15, 3, "Always", 5, 5),

        Mhianswers(16, 4, "Never", 1, 1),
        Mhianswers(17, 4, "Rarely", 2, 2),
        Mhianswers(18, 4, "Sometimes", 3, 3),
        Mhianswers(19, 4, "Often", 4, 4),
        Mhianswers(20, 4, "Always", 5, 5)
    )

    MhiquestionsScreen(
        questions = sampleQuestions,
        answers = sampleAnswers,
        onAnswerSelected = {},
        currentQuestionIndex = 0
    )
}

