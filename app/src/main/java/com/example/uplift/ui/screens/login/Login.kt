package com.example.uplift.ui.screens.login

import android.widget.Toast
import com.example.uplift.ui.theme.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R
import com.example.uplift.ui.composables.*
import com.example.uplift.viewmodels.AuthViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import com.example.uplift.viewmodels.AuthState
import com.example.uplift.ui.theme.Routes

@Composable
fun LoginScreen(navHostController: NavHostController, authViewModel: AuthViewModel) {

    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navHostController.navigate(Routes.HOME)
            is AuthState.Error -> Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 215.dp, height = 110.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(50.dp))
            CustomTextBox(email, hint = "Email", leadingIcon = painterResource(id = R.drawable.user_icon), isPassword = false)
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextBox(password, hint = "Password", leadingIcon = painterResource(id = R.drawable.padlock_icon), isPassword = true)
            Spacer(modifier = Modifier.height(28.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DividerLine()
                Text(
                    text = "Or",
                    style = TextStyle(fontSize = 17.sp, color = Gray,fontFamily = FontFamily(Font(R.font.interbold))),
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                DividerLine()
            }
            Spacer(modifier = Modifier.height(28.dp))
            CustomButton("Use Google Account", painterResource(id = R.drawable.google), onClick = { })
            Spacer(modifier = Modifier.height(12.dp))
            CustomButton("Use Facebook Account", painterResource(id = R.drawable.facebook), onClick = { })
            Spacer(modifier = Modifier.height(10.dp))
            TextButton(
                text = "Forgot password?",
                modifier = Modifier
                    .padding(start = 210.dp, top = 10.dp, bottom = 10.dp),
                onClick = {
                    navHostController.navigate(Routes.SEND_EMAIL)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    authViewModel.login(email.value, password.value)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Cyan),
                modifier = Modifier
                    .size(width = 350.dp, height = 55.dp),
            ) {
                Text(
                    text = "LOGIN",
                    style = TextStyle(fontSize = 22.sp, color = White, fontFamily = FontFamily(Font(R.font.interbold)))
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    style = TextStyle(fontSize = 14.sp, color = DarkGray, fontFamily = FontFamily(Font(R.font.interregular)))
                )
                TextButton(
                    text = "Sign Up",
                    color = Cyan,
                    modifier = Modifier.padding(start = 2.dp),
                    onClick = {
                        navHostController.navigate(Routes.SIGNUP)
                    }
                )
            }
        }
    }
}

@Composable
fun DividerLine() {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(1.dp)
            .background(Black)
    )
}