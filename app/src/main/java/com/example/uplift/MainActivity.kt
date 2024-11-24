package com.example.uplift

import GadViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.uplift.ui.screens.*
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.viewmodels.AuthViewModel
import com.example.uplift.ui.viewmodels.ListTestsViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import com.example.uplift.ui.screens.Questions.GadquestionsScreen
import com.example.uplift.ui.screens.Questions.ListTests
import com.example.uplift.ui.screens.Questions.TestResultsScreen
import com.example.uplift.ui.viewmodels.TestresultsViewModel
import com.google.android.play.core.integrity.t


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel : AuthViewModel by viewModels()
        setContent {
            MainActivityContent(authViewModel)
        }
    }

    override fun onStop() {
        super.onStop()
        // Sign out the user when the activity is stopped
        FirebaseAuth.getInstance().signOut()
    }
}
@Composable
fun MainActivityContent(authViewModel: AuthViewModel) {
    Box() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.LOGIN) {
            composable(Routes.LIST_TESTS) {
                val listTestsViewModel: ListTestsViewModel = viewModel()
                val tests by listTestsViewModel.allTests.observeAsState()
                ListTests(navController = navController, tests = tests!!,
                    onFinish = { testId ->
                        when (testId) {
                            1 -> navController.navigate(Routes.GAD_QUESTIONS)
                            2 -> navController.navigate(Routes.PHQ_QUESTIONS)
                            else -> navController.navigate(Routes.MHI_QUESTIONS)
                        }
                    })
            }
            composable(Routes.GAD_QUESTIONS) {
                val gadViewModel: GadViewModel = viewModel()
                val gadQuestions by gadViewModel.allQuestions.observeAsState()
                val gadAnswers by gadViewModel.allAnswers.observeAsState()
                val currentQuestionIndex by gadViewModel.currentQuestionIndex.observeAsState(0)
                val score by gadViewModel.score.observeAsState(0)

                if (gadQuestions != null && gadAnswers != null) {
                    GadquestionsScreen(
                        navController = navController,
                        questions = gadQuestions!!, // Ensuring not null
                        answers = gadAnswers!!,
                        currentQuestionIndex = currentQuestionIndex,
                        onFinish = { testId,finalScore ->
                            navController.navigate("${Routes.TEST_RESULTS}/${testId}/$finalScore")
                        },
                        onNext = {
                            gadViewModel.moveToNextQuestion()
                        },
                        onPrevious = {
                            gadViewModel.moveToPreviousQuestion()
                        },
                        score = score,
                        onScoreUpdated = { newScore ->
                            gadViewModel.updateScore(newScore)
                        }
                    )
                }
                composable(Routes.MHI_QUESTIONS) {

                }
                composable(Routes.PHQ_QUESTIONS) {

                }
                composable("${Routes.TEST_RESULTS}/{testId}/{score}") { backStackEntry ->
                    val listTestsViewModel: ListTestsViewModel = viewModel()
                    val tests by listTestsViewModel.allTests.observeAsState()
                    val testresultsViewModel: TestresultsViewModel = viewModel()
                    val listTestresults by testresultsViewModel.allTestresults.observeAsState()
                    val testId = backStackEntry.arguments?.getString("testId")?.toIntOrNull()
                    val score = backStackEntry.arguments?.getString("score")?.toIntOrNull()

                    val testresults= listTestresults?.find{it.test_id==testId && score!! >= it.score_min && score <= it.score_max}
                    val testname = tests?.find { it.test_id == testId }?.test_name
                    TestResultsScreen(
                        navController = navController,
                        testname = testname!!,
                        score=score!!,
                        testresults=testresults!!
                        )
                }
            }

        }
    }
}