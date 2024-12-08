package com.example.uplift.ui.composables

import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.uplift.R
import com.example.uplift.ui.theme.DarkGray

@Composable
fun TextButton(text: String, color: Color = DarkGray, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Transparent,
            containerColor = Color.Transparent
        )
    ) {
        Text(
            text = text,
            style = TextStyle(fontSize = 13.sp, color = color, fontFamily = FontFamily(Font(R.font.interregular)))
        )
    }
}