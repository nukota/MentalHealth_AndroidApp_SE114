package com.example.uplift.ui.composables

import com.example.uplift.ui.theme.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.uplift.R

@Composable
fun CustomButton(text: String, icon: Painter?, onClick: () -> Unit,) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.wrapContentSize()
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Black,
                contentColor = White
            ),
            modifier = Modifier
                .width(350.dp)
                .height(55.dp)
        ) {
            if (icon != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp)
                ) {
                    if (icon != null) {
                        Image(
                            painter = icon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(start = 0.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = text,
                        color = Color.Gray,
                        style = TextStyle(fontSize = 17.sp, fontFamily = FontFamily(Font(R.font.interbold))),
                        modifier = Modifier.padding(start = 24.dp)
                    )
                }
            } else {
                Text(
                    text = text,
                    color = Color.LightGray,
                    style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.interbold))),
                    modifier = Modifier.padding(start = 0.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    CustomButton("Use Google Account", painterResource(id = R.drawable.google), onClick = { })
}