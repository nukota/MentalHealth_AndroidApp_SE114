package com.example.uplift

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.uplift.ui.screens.login.LoginScreen
import com.example.uplift.ui.screens.resetPassword.ResetPasswordScreen
import com.example.uplift.ui.screens.sendEmail.SendEmailScreen
import com.example.uplift.ui.screens.signup.SignUpScreen
import com.example.uplift.ui.screens.readStories.ReadStoriesScreen
import com.example.uplift.ui.screens.readStories.StoryDetailScreen
import com.example.uplift.ui.theme.Routes
import com.example.uplift.viewmodels.AuthViewModel
import com.example.uplift.viewmodels.StoryViewModel
import com.example.uplift.viewmodels.ListTestsViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.uplift.ui.screens.ExploreScreen
import com.example.uplift.ui.screens.NavigationBar
import com.example.uplift.ui.screens.habit.HabitScreen
import com.example.uplift.ui.screens.home.HomeScreen
import com.example.uplift.ui.screens.loading.LoadingScreen
import com.example.uplift.ui.screens.questions.ListTests
import com.example.uplift.ui.screens.questions.QuestionsScreen
import com.example.uplift.ui.screens.questions.TestResultsScreen
import com.example.uplift.ui.screens.specialists.ListSpecialistsScreen
import com.example.uplift.viewmodels.HabitViewModel
import com.example.uplift.viewmodels.SpecialistsViewModel
import com.example.uplift.viewmodels.TestResultsViewModel
import com.example.uplift.viewmodels.QuestionsViewModel
import kotlinx.coroutines.delay


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel: AuthViewModel by viewModels()
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainActivityContent(
    authViewModel: AuthViewModel,
) {
    val navController = rememberNavController()
    val storyViewModel: StoryViewModel = viewModel()
    val listTestsViewModel: ListTestsViewModel = viewModel()
    val listSpecialistsViewModel: SpecialistsViewModel = viewModel()
    val questionsViewModel: QuestionsViewModel = viewModel()
    val testResultsViewModel: TestResultsViewModel = viewModel()
    val habitViewModel : HabitViewModel = viewModel()

    var loadingApp by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000) // Delay for 2 seconds
        loadingApp = false
    }

    if (loadingApp) {
        LoadingScreen(authViewModel)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            NavHost(navController = navController, startDestination = Routes.LOGIN) {
                composable(Routes.LOADING) {
                    LoadingScreen(authViewModel)
                }
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
                composable(Routes.SIGNUP) {
                    SignUpScreen(navController, authViewModel)
                }
                composable(Routes.HABIT) {
                    HabitScreen(habitViewModel)
                }
                composable(Routes.EXPLORE) {
                    ExploreScreen(navController)
                }
                composable(Routes.STORY) {
                    ReadStoriesScreen(navController, storyViewModel)
                }
                composable(Routes.STORY_DETAIL) { backStackEntry ->
                    val storyId = backStackEntry.arguments?.getString("storyId")?.toInt() ?: 0
                    StoryDetailScreen(storyId, storyViewModel)
                }
                composable(Routes.LIST_TESTS) {
                    ListTests(navController, listTestsViewModel)
                }
                composable(Routes.QUESTIONS) { backStackEntry ->
                    val testId = backStackEntry.arguments?.getString("testId")?.toIntOrNull() ?: 0
                    val testName = backStackEntry.arguments?.getString("testName") ?: ""
                    QuestionsScreen(testId, testName, navController, questionsViewModel)
                }
                composable(Routes.TEST_RESULTS) { backStackEntry ->
                    val testId = backStackEntry.arguments?.getString("testId")?.toIntOrNull() ?: 0
                    val score = backStackEntry.arguments?.getDouble("score") ?: 0.0
                    val testName = backStackEntry.arguments?.getString("testName") ?: ""
                    TestResultsScreen(testId, testName, score, navController, testResultsViewModel)
                }
                composable(Routes.LIST_SPECIALIST) {
                    ListSpecialistsScreen(navController, authViewModel, listSpecialistsViewModel)
                }
            }
            val currentBackStackEntry = navController.currentBackStackEntryAsState().value
            val currentRoute = currentBackStackEntry?.destination?.route

            if (currentRoute in listOf(Routes.HOME, Routes.HABIT, Routes.DIARY, Routes.EXPLORE, Routes.STORY, Routes.LIST_TESTS, Routes.LIST_SPECIALIST)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 30.dp),
                ) {
                    NavigationBar(navController = navController)
                }
            }
        }
    }
}
