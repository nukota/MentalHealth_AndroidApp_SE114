package com.example.uplift.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.uplift.R
import com.example.uplift.ui.composables.SettingsBox
import com.example.uplift.ui.composables.TopPaddingContent
import com.example.uplift.ui.screens.settings.Logout
import com.example.uplift.ui.theme.Routes
import com.example.uplift.ui.theme.White
import com.example.uplift.viewmodels.AuthViewModel

@Composable
fun MainSettings(
    navController: NavController, authViewModel: AuthViewModel
) {

    val currentUser = authViewModel.userData.observeAsState().value
    //   val currentUser = authViewModel.currentUser
    var showLogoutDialog = remember { mutableStateOf(false) }
    authViewModel.loadUserData()

    if (showLogoutDialog.value) {
        Logout(
            onConfirm = {
                authViewModel.signOut()
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.SETTINGS) { inclusive = true }
                }
                showLogoutDialog.value = false
            },
            onDismiss = {
                showLogoutDialog.value = false
            }
        )
    }

    TopPaddingContent {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f),
                contentScale = ContentScale.Crop
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .zIndex(1f)
                    .fillMaxSize()
            ) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Settings",
                            color = Color(0xff101010),
                            style = TextStyle(
                                fontSize = 32.sp,
                                fontFamily = FontFamily(Font(R.font.lemonada))
                            ),
                            modifier = Modifier
                                .padding(start = 20.dp, top = 10.dp, bottom = 16.dp)
                                .height(54.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
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
                                .padding(start = 10.dp)
                                .size(60.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 20.dp)
                            .fillMaxWidth()
                    ) {
                        currentUser?.display_name?.let {
                            Text(
                                text = it,
                                color = Color.Black,
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                                    fontSize = 20.sp
                                ),
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        currentUser?.email?.let {
                            Text(
                                text = it,
                                color = Color(0xFF007178),
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                                    fontSize = 16.sp
                                ),
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(26.dp))
                SettingsBox(
                    textString = "Edit profile",
                    iconSettings = R.drawable.edit_profile,
                    onClick = { navController.navigate(Routes.EDITPROFILE) }
                )
                Spacer(modifier = Modifier.height(26.dp))
                SettingsBox(
                    textString = "Change password",
                    iconSettings = R.drawable.change_pass,
                    onClick = { navController.navigate(Routes.SEND_EMAIL) }
                )
                Spacer(modifier = Modifier.height(26.dp))
                SettingsBox(
                    textString = "Notifications",
                    iconSettings = R.drawable.notifications,
                    onClick = { navController.navigate(Routes.NOTIFICATION) }
                )
                Spacer(modifier = Modifier.height(26.dp))
                SettingsBox(
                    textString = "Help and Feedback",
                    iconSettings = R.drawable.help,
                    onClick = { navController.navigate(Routes.HELP) }
                )
                Spacer(modifier = Modifier.height(26.dp))
                SettingsBox(
                    textString = "About the app",
                    iconSettings = R.drawable.about,
                    onClick = { navController.navigate(Routes.ABOUT) }
                )
                Spacer(modifier = Modifier.height(26.dp))
                SettingsBox(
                    textString = "Log out",
                    iconSettings = R.drawable.log_out,
                    onClick = {
                        showLogoutDialog.value = true
                    }
                )
                Spacer(modifier = Modifier.height(40.dp))
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
                    Icon(painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable() {
                                navController.popBackStack()
                            })

                    Text(text = "Back",
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .clickable() {
                                navController.popBackStack()
                            })
                }
            }
        }
    }

}

//@Preview(widthDp = 360, heightDp = 800)
//@Composable
//private fun SettingsPreview() {
//    val sampleUser = FirebaseUser(
//
//    )
//    val navController = rememberNavController()
//    MainSettings(user = sampleUser, navController = navController)
//}