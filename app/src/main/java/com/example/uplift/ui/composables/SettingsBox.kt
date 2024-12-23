package com.example.uplift.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R

@Composable
fun SettingsBox(
    textString: String,
    iconSettings:Int,
    onClick: () -> Unit
){
    Row(modifier = Modifier
        .requiredWidth(width = 330.dp)
        .requiredHeight(height = 48.dp)
        .border( // Đặt border bên ngoài clip
            BorderStroke(1.dp, Color(0xFF000000)),
            shape = RoundedCornerShape(20.dp)
        )
        .clip(RoundedCornerShape(18.dp))
        .background(Color.White)
        .padding(horizontal = 20.dp, vertical = 8.dp)
        .fillMaxWidth()
        .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = iconSettings),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = textString,
            color = Color(0xFF101010),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.inter)),
                fontSize = 16.sp
            ),
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = null,
            modifier = Modifier
                .width(14.dp)
                .height(17.dp)
        )
    }
}