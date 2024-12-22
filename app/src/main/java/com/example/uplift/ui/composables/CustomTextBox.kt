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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import com.example.uplift.R

@Composable
fun CustomTextBox(text: MutableState<String>, hint: String, leadingIcon: Painter, isPassword: Boolean = false) {
    val visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(350.dp)
            .height(55.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(27.5.dp)
            )
            .border(
                width = 1.5.dp,
                color = Color(0xFF505050),
                shape = RoundedCornerShape(27.5.dp)
            )
    ) {
        TextField(
            leadingIcon = {
                Image(
                    painter = leadingIcon,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(DarkGray),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(22.dp)
                )
            },
            value = text.value,
            placeholder = {
                Text(
                    text = hint,
                    color = Gray,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            onValueChange = { text.value = it },
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
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 15.sp,
                color = Black,
                fontFamily = FontFamily(Font(R.font.interregular)),
            ),
            visualTransformation = visualTransformation,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
                .height(55.dp)
        )
    }
}