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
import com.example.uplift.ui.screens.Questions.ListTests
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.viewmodels.AuthViewModel
import com.example.uplift.ui.viewmodels.ListTestsViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*


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
            }
            composable(Routes.GAD_QUESTIONS) {
                val gadViewModel: GadViewModel = viewModel()
                val gadquestions by gadViewModel.allQuestions.observeAsState()
                val currentQuestionIndex by gadViewModel.currentQuestionIndex.observeAsState(0)
            }
            composable(Routes.MHI_QUESTIONS) {

            }
            composable(Routes.PHQ_QUESTIONS) {

            }
        }

    }
}