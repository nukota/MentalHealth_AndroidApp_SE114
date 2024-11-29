package com.example.uplift.ui.screens.resetPassword

import com.example.uplift.ui.theme.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.uplift.R
import com.example.uplift.ui.composables.*
import com.example.uplift.viewmodels.AuthViewModel

@Composable
fun ResetPasswordScreen(navHostController: NavHostController, authViewModel: AuthViewModel) {

    var password1 = remember { mutableStateOf("") }
    var password2 = remember { mutableStateOf("") }

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
            Spacer(modifier = Modifier.height(100.dp))
            Text("Reset Password", style = TextStyle(fontSize = 24.sp, color = DarkGray, fontFamily = FontFamily(Font(R.font.interregular))))
            Spacer(modifier = Modifier.height(30.dp))
            CustomTextBox(password1, "Enter New Password", painterResource(id = R.drawable.padlock_icon))
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextBox(password2, "Confirm New Password", painterResource(id = R.drawable.padlock_icon))
            Spacer(modifier = Modifier.height(28.dp))
            CustomButton("Reset Password", null, onClick = { })
            Spacer(modifier = Modifier.height(150.dp))
        }
    }
}