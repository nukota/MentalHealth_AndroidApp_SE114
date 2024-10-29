package com.example.uplift.ui.composables

import com.example.uplift.ui.theme.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.uplift.R

@Composable
fun TextboxShape(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    width: Dp = 350.dp,
    height: Dp = 55.dp,
    cornerRadius: Dp = 27.5.dp,
    strokeColor: Color = Color(0xFF505050), // dark_gray
    strokeWidth: Dp = 1.5.dp
) {
    Box(
        modifier = modifier
            .size(width, height)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .border(
                width = strokeWidth,
                color = strokeColor,
                shape = RoundedCornerShape(cornerRadius)
            )
    )
}

@Composable
fun CustomTextBox(hint: String, leadingIcon: Painter) {
    Box() {
        TextboxShape() // Use the Textbox composable as the background
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 24.dp, end = 0.dp)
        ) {
            Image(
                painter = leadingIcon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(DarkGray),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(22.dp)
            )
            TextField(
                value = "",
                placeholder = { Text(text = hint, color = Gray, fontSize = 17.sp,) },
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 17.sp, color = Black, fontFamily = FontFamily(Font(R.font.interregular))),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTextBoxPreview() {
    CustomTextBox("Username", painterResource(id = R.drawable.user_icon))
}