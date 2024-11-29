package com.example.uplift

import QuestionsViewModel
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.uplift.ui.screens.home.HomeScreen
import com.example.uplift.ui.screens.login.LoginScreen
import com.example.uplift.ui.screens.resetPassword.ResetPasswordScreen
import com.example.uplift.ui.screens.sendEmail.SendEmailScreen
import com.example.uplift.ui.screens.signup.SignUpScreen
import com.example.uplift.ui.screens.readStories.ReadStoriesScreen
import com.example.uplift.ui.screens.readStories.StoryDetailScreen
import com.example.uplift.ui.theme.Routes
import com.example.uplift.viewmodels.AuthViewModel
import com.example.uplift.viewmodels.StoryViewModel
import com.example.uplift.ui.viewmodels.ListTestsViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import com.example.uplift.data.models.Answer
import com.example.uplift.data.models.Questions
import com.example.uplift.ui.screens.questions.ListTests
import com.example.uplift.ui.screens.questions.QuestionsScreen
import com.example.uplift.ui.screens.questions.TestResultsScreen
import com.example.uplift.ui.screens.specialists.ListSpecialistsScreen
import com.example.uplift.ui.viewmodels.SpecialistsViewModel
import com.example.uplift.ui.viewmodels.TestresultsViewModel


class MainActivity : AppCompatActivity() {
    private val storyViewModel: StoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel : AuthViewModel by viewModels()
        setContent {
            MainActivityContent(authViewModel, storyViewModel)
        }
    }

    override fun onStop() {
        super.onStop()
        // Sign out the user when the activity is stopped
        FirebaseAuth.getInstance().signOut()
    }
}

@Composable
fun MainActivityContent(
    authViewModel: AuthViewModel,
    storyViewModel: StoryViewModel
) {
    val navController = rememberNavController()

    Box() {
        NavHost(navController = navController, startDestination = Routes.STORY) {
            composable(Routes.HOME) {
                HomeScreen(navController, authViewModel)
            }
            composable(Routes.LOGIN) {
                LoginScreen(navController, authViewModel)
            }
            composable(Routes.SEND_EMAIL) {
                SendEmailScreen(navController, authViewModel)
            }
            composable(Routes.RESET_PASSWORD) {
                ResetPasswordScreen(navController, authViewModel)
            }
            composable(Routes.SIGNUP){
                SignUpScreen(navController, authViewModel)
            }
            composable(Routes.STORY) {
                ReadStoriesScreen(navController, storyViewModel)
            }
            composable(Routes.STORY_DETAIL) { backStackEntry ->
                val storyId = backStackEntry.arguments?.getString("storyId")?.toInt() ?: 0
                StoryDetailScreen(storyId, storyViewModel)
            }
            composable(Routes.LIST_TESTS) {
                val listTestsViewModel: ListTestsViewModel = viewModel()
                val tests by listTestsViewModel.allTests.observeAsState()
                ListTests(
                    navController = navController,
                    tests = tests ?: emptyList(),
                    onFinish = { testId, testName ->
                        navController.navigate("${Routes.QUESTIONS}/$testId/$testName")
                    })
            }

            composable("${Routes.QUESTIONS}/{testId}/{testName}") { backStackEntry ->
                val questionsViewModel: QuestionsViewModel = viewModel()
                val questions by questionsViewModel.allQuestions.observeAsState()
                val answers by questionsViewModel.allAnswers.observeAsState()
                val currentQuestionIndex by questionsViewModel.currentQuestionIndex.observeAsState(0)
                val score by questionsViewModel.score.observeAsState(0.0)

                val testId = backStackEntry.arguments?.getString("testId")?.toIntOrNull()
                val testName = backStackEntry.arguments?.getString("testName")
                val listQuestion: List<Questions> = questions!!.filter { it.test_id == testId }
                val listAnswer: List<Answer> = answers!!.filter { it.test_id == testId }
                QuestionsScreen(
                    navController = navController,
                    questions = listQuestion,
                    answers = listAnswer,
                    currentQuestionIndex = currentQuestionIndex,
                    testId = testId!!,
                    onNext = {
                        questionsViewModel.moveToNextQuestion()
                    },
                    onPrevious = {
                        questionsViewModel.moveToPreviousQuestion()
                    },
                    score = score,
                    testName = testName!!,
                    onScoreUpdated = { newScore ->
                        questionsViewModel.updateScore(newScore)
                    },
                    onFinish = { testId, testName, finalScore ->
                        navController.navigate("${Routes.TEST_RESULTS}/${testId}/$finalScore")
                    }
                )
            }
            composable("${Routes.TEST_RESULTS}/{testId}/{testName}/{score}") { backStackEntry ->
                val listTestsViewModel: ListTestsViewModel = viewModel()
                val tests by listTestsViewModel.allTests.observeAsState()
                val testresultsViewModel: TestresultsViewModel = viewModel()
                val listTestresults by testresultsViewModel.allTestresults.observeAsState()
                val testId = backStackEntry.arguments?.getString("testId")?.toIntOrNull()
                val score = backStackEntry.arguments?.getString("score")?.toIntOrNull()
                val testName = backStackEntry.arguments?.getString("testName")

                val testresults = listTestresults?.find { it.test_id == testId && score!! >= it.score_min && score <= it.score_max }
                TestResultsScreen(
                    navController = navController,
                    testName = testName!!,
                    score = score!!,
                    testresults = testresults!!
                )
            }
            composable(Routes.LIST_SPECIALIST) {backStackEntry->
                val email = backStackEntry.arguments?.getString("email")
                val listSpecialistsViewModel: SpecialistsViewModel = viewModel()
                val specialists by listSpecialistsViewModel.allSpecialists.observeAsState()
                if (email != null) {
                    ListSpecialistsScreen(
                        navController = navController,
                        email=email,
                        listSpecialists = specialists ?: emptyList())
                }
            }
        }
//        NavigationBar(modifier = Modifier
//            .align(Alignment.BottomCenter)
//        )

    }
}
