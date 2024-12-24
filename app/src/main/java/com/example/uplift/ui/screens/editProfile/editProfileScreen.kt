package com.example.uplift.ui.screens.editProfile

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.uplift.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.compose.rememberNavController
import com.example.uplift.data.models.User
import com.example.uplift.ui.composables.TopPaddingContent
import com.example.uplift.ui.theme.White
import com.example.uplift.viewmodels.AuthViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditProfile(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    // Tải dữ liệu người dùng
    authViewModel.loadUserData()

    // Lấy dữ liệu người dùng từ LiveData
    val userData by authViewModel.userData.observeAsState()

    // Nếu dữ liệu người dùng chưa được tải hoặc đang null, hiển thị thông báo hoặc loading
    if (userData == null) {
        // Hiển thị thông báo hoặc loading (ví dụ sử dụng CircularProgressIndicator)
        CircularProgressIndicator()
        return
    }

    // Sử dụng dữ liệu người dùng để khởi tạo giá trị các trường nhập liệu
    var displayname by remember { mutableStateOf(userData?.display_name ?: "") }
    var pronouns by remember { mutableStateOf(userData?.pronouns ?: "") }
    var date by remember { mutableStateOf(userData?.date ?: "") }
    var phonenumber by remember { mutableStateOf(userData?.phone ?: "") }
    var location by remember { mutableStateOf(userData?.location ?: "") }

    // Giao diện người dùng (như đã có trước đó)
    TopPaddingContent {
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
                    .fillMaxSize()
                    .alpha(0.5f),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Edit Profile",
                        color = Color(0xff101010),
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontFamily = FontFamily(Font(R.font.lemonada))
                        ),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 10.dp, bottom = 16.dp)
                            .height(54.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.prof),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .clickable { }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Text and BasicTextField
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                ) {
                    @Composable
                    fun createInputField(
                        label: String,
                        placeholderText: String,
                        value: String,
                        onValueChange: (String) -> Unit
                    ) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = FontFamily(Font(R.font.lemonada)),
                                fontSize = 20.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        BasicTextField(
                            value = value,
                            onValueChange = onValueChange,
                            modifier = Modifier
                                .fillMaxWidth()
                                .drawBehind {
                                    drawLine(
                                        color = Color(0xff999999),
                                        start = Offset(0f, size.height),
                                        end = Offset(size.width, size.height),
                                        strokeWidth = 2.dp.toPx()
                                    )
                                },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = FontFamily(Font(R.font.lemonada)),
                                fontSize = 16.sp,
                                color = Color.Black
                            ),
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .padding(vertical = 4.dp)
                                ) {
                                    if (value.isEmpty()) {
                                        Text(
                                            text = placeholderText,
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                fontFamily = FontFamily(Font(R.font.lemonada)),
                                                fontSize = 16.sp,
                                                color = Color.Gray
                                            )
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    // Phone Number
                    createInputField("Display Name", "Enter your display name", displayname) {
                        displayname = it
                    }

                    // Email
                    createInputField("Pronouns", "He/She/Mr/Ms...", pronouns) {
                        pronouns = it
                    }

                    // Address
                    createInputField("Date of Birth", "DD-MM-YYYY", date) {
                        date = it
                    }

                    // Location
                    createInputField("Phone Number", "Enter your phone number", phonenumber) {
                        phonenumber = it
                    }

                    // Gender
                    createInputField("Location", "Enter your location", location) {
                        location = it
                    }

                    // Button below the second column
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = {
                            if (authViewModel.currentUserUid.isBlank()) {
                                Log.e("EditProfile", "Current user UID is missing!")
                                return@Button
                            }

                            // Lấy email từ AuthViewModel
                            val userEmail = authViewModel.getUserEmail()

                            if (userEmail.isNullOrBlank()) {
                                Log.e("EditProfile", "User email is missing!")
                                return@Button
                            }

                            val updatedUser = User(
                                uid = authViewModel.currentUserUid,
                                display_name = displayname,
                                email = userEmail, // Email tự động thêm
                                phone = phonenumber,
                                location = location,
                                pronouns = pronouns,
                                date = date
                            )

                            Log.d("EditProfile", "Preparing to save user data: $updatedUser")

                            authViewModel.saveUserData(
                                user = updatedUser,
                                onSuccess = {
                                    Log.d("EditProfile", "User data saved successfully")
                                    navController.navigateUp()
                                },
                                onFailure = { exception ->
                                    Log.e("EditProfile", "Failed to save user data: ${exception.message}")
                                }
                            )
                        }
                    ) {
                        Text("Save")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
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
}

//@RequiresApi(Build.VERSION_CODES.O)
//@androidx.compose.ui.tooling.preview.Preview
//@Composable
//private fun EditProfilePreview() {
 //   val navController = rememberNavController()
  //  EditProfile(
  //      navController = navController,
  //      authViewModel = authViewModel // Provide mock or empty parameters as required
 //   )
//}
