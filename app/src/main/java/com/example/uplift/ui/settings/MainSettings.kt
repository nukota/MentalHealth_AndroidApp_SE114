package com.example.uplift.ui.screens.Questions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.uplift.R
import com.example.uplift.data.models.User
import com.example.uplift.ui.composables.QuestionBox
import com.example.uplift.ui.composables.SettingsBox
import com.example.uplift.ui.theme.White

@Composable
fun MainSettings (
    user: User,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background2),
            contentDescription = null,
            modifier = Modifier
                .size(800.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .zIndex(1f)
                .fillMaxSize()

        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
            {
                Column(
                ) {
                    Text(
                        text = "Settings",
                        color = Color(0xff5FE4D4),
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = FontFamily(Font(R.font.lemonada))
                        ),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp)
                            .height(43.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(40.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp, top = 28.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .requiredHeight(height = 90.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color(0xffF8F8F8))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .width(90.dp)
                        .fillMaxHeight()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avartar1),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 30.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = user.display_name,
                        color = Color(0xFF000000),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                            fontSize = 30.sp
                        ),
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = user.email,
                        color = Color(0xFF007178),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                            fontSize = 16.sp
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            SettingsBox(
                textString = "Edit profile",
                iconSettings = R.drawable.edit_profile,
                onClick = {
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            SettingsBox(
                textString = "Change password",
                iconSettings = R.drawable.change_pass,
                onClick = {
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            SettingsBox(
                textString = "Notifications",
                iconSettings = R.drawable.notifications,
                onClick = {
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            SettingsBox(
                textString = "Help and Feedback",
                iconSettings = R.drawable.help,
                onClick = {
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            SettingsBox(
                textString = "About the app",
                iconSettings = R.drawable.about,
                onClick = {
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            SettingsBox(
                textString = "Log out",
                iconSettings = R.drawable.log_out,
                onClick = {
                }
            )
            Spacer(modifier = Modifier.height(65.dp))
            Row(
                modifier = Modifier
                    .requiredHeight(height = 30.dp)
            ) {
                Text(
                    text = "Uplift",
                    color = Color(0xFF505050),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                        fontSize = 16.sp
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .requiredHeight(height = 30.dp)
            ) {
                Text(
                    text = "Version 1.1.0",
                    color = Color(0xFF999999),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                        fontSize = 12.sp
                    ),
                )
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 20.dp, bottom = 28.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "back",
                    modifier = Modifier
                        .size(24.dp)
                )
                Text(
                    text = "Back",
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun SettingsPreview() {
    val sampleUser = User(
        display_name = "Cong Thanh",
        email = "22521351@gm.uit.edu.vn",
        photo_url = "",
        role = "admin",
        uid = "iStKOQQ0TmdNL6FDRninshnjdwl1"
    )
    MainSettings(user = sampleUser)
}