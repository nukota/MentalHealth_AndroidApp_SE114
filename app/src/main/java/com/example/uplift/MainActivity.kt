package com.example.uplift

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
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
import com.example.uplift.ui.viewmodels.AuthViewModel
import com.example.uplift.ui.viewmodels.StoryViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
fun MainActivityContent(authViewModel: AuthViewModel, storyViewModel: StoryViewModel) {
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
        }
//        NavigationBar(modifier = Modifier
//            .align(Alignment.BottomCenter)
//        )

    }
}
