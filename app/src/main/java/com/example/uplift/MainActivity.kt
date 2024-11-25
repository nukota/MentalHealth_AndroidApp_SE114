package com.example.uplift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.diary.ui.DiaryScreen
import com.example.uplift.data.database.DiaryDatabase
import com.example.uplift.data.models.Diary
import com.example.uplift.logic.repository.DiaryRepository
import com.example.uplift.ui.screens.diary.AddDiaryScreen
import com.example.uplift.ui.screens.diary.EditDiaryScreen
import com.example.uplift.ui.screens.home.HomeScreen
import com.example.uplift.ui.screens.login.LoginScreen
import com.example.uplift.ui.screens.resetPassword.ResetPasswordScreen
import com.example.uplift.ui.screens.sendEmail.SendEmailScreen
import com.example.uplift.ui.screens.signup.SignUpScreen
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.viewmodels.AuthViewModel
import com.example.uplift.ui.viewmodels.DiaryModel
import com.example.uplift.ui.viewmodels.DiaryViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    lateinit var diaryModel: DiaryModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setupViewModel()

        val authViewModel : AuthViewModel by viewModels()
        setContent {
            MainActivityContent(authViewModel, diaryModel)
        }

        }
    private fun setupViewModel(){
        val diaryRepository = DiaryRepository(DiaryDatabase(this))
        val viewModelProviderFactory = DiaryViewModelFactory(application, diaryRepository)
        diaryModel = ViewModelProvider(this, viewModelProviderFactory)[DiaryModel::class]

    }

    override fun onStop() {
        super.onStop()
        // Sign out the user when the activity is stopped
        FirebaseAuth.getInstance().signOut()
    }
}
@Composable
fun MainActivityContent(authViewModel: AuthViewModel, diaryModel: DiaryModel) {
    Box() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.LOGIN ) {
         composable(Routes.HOME) {
         HomeScreen(navController, authViewModel)
        }
          composable(Routes.LOGIN) {
              LoginScreen(navController, authViewModel)
           }

            composable(Routes.DIARY) {
                DiaryScreen(viewModel = diaryModel, navController = navController)
            }
            composable(Routes.ADDDIARY) {
                AddDiaryScreen(onSave = { title, content ->
                    diaryModel.addDairy(
                        Diary(
                            title = title,
                            content = content,
                            date_created = System.currentTimeMillis(), // Dùng Long
                            date_modified = System.currentTimeMillis() // Dùng Long
                        )
                    )
                    navController.popBackStack() // Quay lại DiaryScreen sau khi lưu
                })
            }

            composable("edit diary/{diary_id}") { backStackEntry ->
                val diary_id = backStackEntry.arguments?.getString("diary_id")?.toInt() ?: -1
                EditDiaryScreen(diary_id = diary_id, navController = navController, viewModel = diaryModel)
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