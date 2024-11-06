package com.example.uplift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.uplift.ui.screens.*
import com.example.uplift.ui.screens.home.HomeScreen
import com.example.uplift.ui.screens.login.LoginScreen
import com.example.uplift.ui.screens.resetPassword.ResetPasswordScreen
import com.example.uplift.ui.screens.sendEmail.SendEmailScreen
import com.example.uplift.ui.screens.signup.SignUpScreen
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
        NavHost(navController = navController, startDestination = Routes.LOGIN) {
            composable(Routes.HOME) {
                HomeScreen(navController, authViewModel)
            }
            composable(Routes.LOGIN) {
                LoginScreen(navController, authViewModel)
            }
            composable(Routes.SENDEMAIL) {
                SendEmailScreen(navController, authViewModel)
            }
            composable(Routes.RESETPASSWORD) {
                ResetPasswordScreen(navController, authViewModel)
            }
            composable(Routes.SIGNUP){
                SignUpScreen(navController, authViewModel)
            }
        }
//        NavigationBar(modifier = Modifier
//            .align(Alignment.BottomCenter)
//        )

    }
}
