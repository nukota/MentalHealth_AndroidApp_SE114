package com.example.uplift.ui.screens.login

import com.example.uplift.ui.theme.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R
import com.example.uplift.ui.composables.*

@Composable
fun LoginScreen() {
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
            CustomTextBox("Username", painterResource(id = R.drawable.user_icon))
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextBox("Password", painterResource(id = R.drawable.padlock_icon))
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
                    .padding(start = 210.dp, top = 10.dp, bottom = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Cyan),
                modifier = Modifier
                    .size(width = 350.dp, height = 55.dp)
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
                    style = TextStyle(fontSize = 17.sp, color = DarkGray, fontFamily = FontFamily(Font(R.font.interregular)))
                )
                TextButton(
                    text = "Sign Up",
                    color = Cyan,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

@Composable
fun TextButton(text: String, color: Color = DarkGray, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(fontSize = 17.sp, color = color, fontFamily = FontFamily(Font(R.font.interregular))),
        modifier = modifier
    )
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

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}