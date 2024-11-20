package com.example.uplift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

import com.example.uplift.ui.screens.habit.HabitScreen
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.viewmodels.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

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
        NavHost(navController = navController, startDestination = Routes.HABIT) {
            composable(Routes.HABIT) {
                HabitScreen()
            }

          //  composable(Routes.HOME) {
        //        HabitScreen(navController, authViewModel)
         //   }

            //           composable(Routes.LOGIN) {
            //               LoginScreen(navController, authViewModel)
            //           }
            //         composable(Routes.SENDEMAIL) {
            //               SendEmailScreen(navController, authViewModel)
            //          }
            //         composable(Routes.RESETPASSWORD) {
            //              ResetPasswordScreen(navController, authViewModel)
            //         }
            //         composable(Routes.SIGNUP){
            //             SignUpScreen(navController, authViewModel)
            //         }
            //     }
//        NavigationBar(modifier = Modifier
//            .align(Alignment.BottomCenter)
//        )

        }
    }
}
