package com.example.uplift.ui.screens.sendEmail

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
fun SendEmailScreen(navHostController: NavHostController, authViewModel: AuthViewModel) {

    var verifyingEmail = remember { mutableStateOf("") }
    val context = LocalContext.current
    val currentUserEmail = authViewModel.getUserEmail() // Assuming this property exists in AuthViewModel

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
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                "You will receive a password reset link in your email.\nClick the link to reset your password.",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Gray,
                    fontFamily = FontFamily(Font(R.font.interregular))
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            CustomTextBox(
                verifyingEmail,
                "Enter Your Email",
                painterResource(id = R.drawable.email_icon)
            )
            Spacer(modifier = Modifier.height(28.dp))
            CustomButton("Send mail", null, onClick = {
                if (currentUserEmail == null || verifyingEmail.value == currentUserEmail) {
                    authViewModel.sendPasswordResetEmail(verifyingEmail.value) { success ->
                        if (success) {
                            Toast.makeText(context, "Password reset email sent.", Toast.LENGTH_SHORT).show()
                            navHostController.navigate(Routes.LOGIN)
                        } else {
                            Toast.makeText(context, "Failed to send password reset email.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Entered email does not match the current user's email.", Toast.LENGTH_SHORT).show()
                }
            })
            Spacer(modifier = Modifier.height(220.dp))
        }
    }
}