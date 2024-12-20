package com.example.uplift.ui.screens.settings

import ButtonSettings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uplift.R

@Composable
fun Logout(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val commonTextStyle = TextStyle(
        color = Color(0xFF505050),
        fontFamily = FontFamily(Font(R.font.inter)),
        fontSize = 13.sp
    )

    AlertDialog(
        onDismissRequest = { onDismiss() },
        text = {
            Text(
                text = "Are you sure you want to log out?",
                style = commonTextStyle.copy(fontWeight = FontWeight.Bold)
            )
        },
        confirmButton = {
            Button(
                onClick = {onConfirm()},
                colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = Color.Black),
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .requiredHeight(35.dp)
                    .background(Color(0xFF5FE4D4))
            ) {
                Text(
                    text = "Log out",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black,
                        fontFamily = FontFamily(Font(R.font.interbold))
                    ),

                )
            }
        },

        dismissButton = {
            Button(
                onClick = {onDismiss()},
                colors = ButtonDefaults.buttonColors(Color.Transparent, contentColor = Color.Black),
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .requiredHeight(35.dp)
                    .background(Color(0xFFFBBC05))
            ) {
                Text(
                    text = "Cancel",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black,
                        fontFamily = FontFamily(Font(R.font.interbold))
                    ),
                    )
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewLogoutDialog() {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        Logout(
            onConfirm = {
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
}
