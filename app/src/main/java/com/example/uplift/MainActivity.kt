package com.example.uplift

import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.uplift.ui.screens.diary.DiaryScreen
import com.example.uplift.ui.screens.ExploreScreen
import com.example.uplift.ui.screens.NavigationBar
import com.example.uplift.ui.screens.settings.AboutApp
import com.example.uplift.ui.screens.settings.MainSettings
import com.example.uplift.ui.screens.diary.AddDiaryScreen
import com.example.uplift.ui.screens.diary.UpdateDiaryScreen
import com.example.uplift.ui.screens.editProfile.EditProfile
import com.example.uplift.ui.screens.habit.HabitDetailScreen
import com.example.uplift.ui.screens.habit.HabitScreen
import com.example.uplift.ui.screens.home.HomeScreen
import com.example.uplift.ui.screens.loading.LoadingScreen
import com.example.uplift.ui.screens.questions.ListTests
import com.example.uplift.ui.screens.questions.QuestionsScreen
import com.example.uplift.ui.screens.questions.TestResultsScreen
import com.example.uplift.ui.screens.settings.Help
import com.example.uplift.ui.screens.settings.Notification
import com.example.uplift.ui.screens.specialists.ListSpecialistsScreen
import com.example.uplift.viewmodels.DiaryViewModel
import com.example.uplift.viewmodels.HabitViewModel
import com.example.uplift.viewmodels.NotificationViewModel
import com.example.uplift.viewmodels.SpecialistsViewModel
import com.example.uplift.viewmodels.TestResultsViewModel
import com.example.uplift.viewmodels.QuestionsViewModel
import kotlinx.coroutines.delay


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
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

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun MainActivityContent(
    authViewModel: AuthViewModel,
) {
    val view = LocalView.current
    val density = LocalDensity.current
    val bottomInset = with(density) {
        androidx.core.view.ViewCompat.getRootWindowInsets(view)
            ?.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars())?.bottom?.toDp()
            ?: 0.dp
    }
    val navController = rememberNavController()
    val notificationViewModel: NotificationViewModel = viewModel()
    val storyViewModel: StoryViewModel = viewModel()
    val listTestsViewModel: ListTestsViewModel = viewModel()
    val listSpecialistsViewModel: SpecialistsViewModel = viewModel()
    val questionsViewModel: QuestionsViewModel = viewModel()
    val testResultsViewModel: TestResultsViewModel = viewModel()
    val habitViewModel: HabitViewModel = viewModel()
    val uid = authViewModel.getUserUid() ?: ""

    var loadingApp by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(1000) // Delay for 2 seconds
        loadingApp = false
    }

    if (loadingApp) {
        LoadingScreen(authViewModel)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomInset)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                NavHost(navController = navController, startDestination = Routes.LOGIN) {
                    composable(Routes.LOADING) {
                        LoadingScreen(authViewModel)
                    }
                    composable(Routes.HOME) {
                        HomeScreen(navController, authViewModel, habitViewModel)
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
                        HabitScreen(uid, navController, habitViewModel)
                    }
                    composable(Routes.HABIT_DETAIL) { backStackEntry ->
                        val habitId = backStackEntry.arguments?.getString("habitId")?.toInt() ?: 0
                        HabitDetailScreen(habitId, navController, habitViewModel)
                    }
                    composable(Routes.EXPLORE) {
                        ExploreScreen(navController)
                    }
                    composable(Routes.STORY) {
                        ReadStoriesScreen(navController, storyViewModel)
                    }
                    composable(Routes.STORY_DETAIL) { backStackEntry ->
                        val storyId = backStackEntry.arguments?.getString("storyId")?.toInt() ?: 0
                        StoryDetailScreen(storyId, navController, storyViewModel)
                    }
                    composable(Routes.LIST_TESTS) {
                        ListTests(navController, listTestsViewModel,questionsViewModel)
                    }
                    composable(Routes.QUESTIONS) { backStackEntry ->
                        val testId =
                            backStackEntry.arguments?.getString("testId")?.toIntOrNull() ?: 0
                        val testName = backStackEntry.arguments?.getString("testName") ?: ""
                        QuestionsScreen(testId, testName, navController, questionsViewModel)
                    }
                    composable(Routes.TEST_RESULTS) { backStackEntry ->
                        val testId =
                            backStackEntry.arguments?.getString("testId")?.toIntOrNull() ?: 0
                        val score = backStackEntry.arguments?.getString("score")?.toDoubleOrNull() ?: 0.0
                        val testName = backStackEntry.arguments?.getString("testName") ?: ""
                        TestResultsScreen(
                            testId,
                            testName,
                            score,
                            navController,
                            testResultsViewModel
                        )
                    }
                    composable(Routes.LIST_SPECIALIST) {
                        ListSpecialistsScreen(
                            navController,
                            authViewModel,
                            listSpecialistsViewModel
                        )
                    }
                    composable(Routes.DIARY) {
                        val diaryViewModel: DiaryViewModel = viewModel()
                        DiaryScreen(navController, diaryViewModel)
                    }
                    composable(Routes.DIARY_ADD) {
                        val diaryViewModel: DiaryViewModel = viewModel()
                        AddDiaryScreen(
                            uid = uid,
                            diaryViewModel = diaryViewModel,
                            navController = navController
                        )
                    }
                    composable(Routes.DIARY_UPDATE) { backStackEntry ->
                        val diaryId = backStackEntry.arguments?.getString("diaryId")?.toInt() ?: 0
                        val diaryViewModel: DiaryViewModel = viewModel()
                        Log.d("MainActivity", "Diary ID: $diaryId")
                        UpdateDiaryScreen(
                            diaryId = diaryId,
                            diaryViewModel = diaryViewModel,
                            navController = navController
                        )
                    }
                    composable(Routes.ABOUT) {
                        AboutApp(navController = navController)
                    }
                    composable(Routes.HELP) {
                        Help(navController = navController)
                    }
                    composable(Routes.SETTINGS) {
                        MainSettings(navController, authViewModel)
                    }
                    composable(Routes.EDITPROFILE) {
                        EditProfile(navController, authViewModel)
                    }
                    composable(Routes.NOTIFICATION) {
                        Notification(notificationViewModel, navController)
                    }

                }
            }
            val currentBackStackEntry = navController.currentBackStackEntryAsState().value
            val currentRoute = currentBackStackEntry?.destination?.route

            if (currentRoute in listOf(
                    Routes.HOME,
                    Routes.HABIT,
                    Routes.DIARY,
                    Routes.EXPLORE,
                    Routes.STORY,
                    Routes.LIST_TESTS,
                    Routes.LIST_SPECIALIST
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) { NavigationBar(navController = navController) }
            }
        }
    }
}
