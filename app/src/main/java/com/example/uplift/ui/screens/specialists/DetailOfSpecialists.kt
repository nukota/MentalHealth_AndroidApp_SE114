package com.example.uplift.ui.screens.specialists

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.uplift.R
import com.example.uplift.data.models.Specialist
import com.example.uplift.ui.composables.SendEmail
import com.example.uplift.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailOfSpecialists(
    specialist: Specialist,
    useremail: String,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val commonTextStyle = TextStyle(
        color = Color(0xFF505050),
        fontFamily = FontFamily(Font(R.font.inter)),
        fontSize = 13.sp
    )
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        content = {
            Surface(
                modifier = Modifier
                    .width(337.dp)
                    .height(566.dp)
                    .padding(16.dp),
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(3.dp, Color.Black)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        IconButton(onClick = onDismiss) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color(0xFF007178)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .width(337.dp)
                            .height(174.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(specialist.avatar),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(10.dp)
                                    .size(96.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(top = 10.dp)
                        ) {
                            Text(
                                text = specialist.full_name,
                                color = Color(0xFF007178),
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                                    fontSize = 16.sp
                                ),
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(
                            ) {
                                Text(
                                    text = "Age: ",
                                    fontWeight = FontWeight.Bold,
                                    style = commonTextStyle
                                )
                                Text(
                                    text = specialist.age.toString(),
                                    style = commonTextStyle
                                )
                            }

                            Text(
                                text = "Profession: ",
                                fontWeight = FontWeight.Bold,
                                style = commonTextStyle
                            )
                            Text(
                                text = specialist.profession,
                                style = commonTextStyle
                            )
                            Row(
                            ) {
                                Text(
                                    text = "Year of Experience: ",
                                    fontWeight = FontWeight.Bold,
                                    style = commonTextStyle
                                )
                                Text(
                                    text = "${specialist.years_of_experience}",
                                    style = commonTextStyle
                                )
                            }
                            Row(
                            ) {
                                Text(
                                    text = "Location: ",
                                    fontWeight = FontWeight.Bold,
                                    style = commonTextStyle
                                )
                                Text(
                                    text = specialist.location,
                                    color = Color(0xFF505050),
                                    style = commonTextStyle
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(17.dp)
                                    .padding(start = 50.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .fillMaxHeight()
                                )
                                Text(
                                    text = "${specialist.rating} (${specialist.review_count} reviews)",
                                    color = Color(0xFF787300),
                                    textDecoration = TextDecoration.Underline,
                                    fontFamily = FontFamily(Font(R.font.sansitadwashedfont)),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                    Column(modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp)
                        .fillMaxWidth()) {
                        Row {
                            Text(
                                text = "Specializations: ",
                                fontWeight = FontWeight.Bold,
                                style = commonTextStyle
                            )
                        }
                        Column(modifier = Modifier.padding(start = 10.dp)) {
                            specialist.specializations.forEach { specialization ->
                                Row {
                                    Text(
                                        text = "• $specialization",
                                        style = commonTextStyle,
                                        fontSize = 14.sp,
                                    )
                                }
                            }
                        }
                    }
                    Column(modifier = Modifier
                        .padding(start = 10.dp, top = 20.dp)
                        .fillMaxWidth()) {
                        Text(
                            text = "Educations: ",
                            fontWeight = FontWeight.Bold,
                            style = commonTextStyle
                        )
                        Column(modifier = Modifier.padding(start = 10.dp)) {
                            specialist.educations.forEach { education ->
                                Row {
                                    Text(
                                        text = "• $education",
                                        style = commonTextStyle,
                                        fontSize = 14.sp,
                                    )
                                }
                            }
                        }
                    }
                    Column(modifier = Modifier
                        .padding(start = 10.dp, top = 20.dp)
                        .fillMaxWidth()) {
                        Text(
                            text = "Certifications: ",
                            fontWeight = FontWeight.Bold,
                            style = commonTextStyle
                        )
                        Column(modifier = Modifier.padding(start = 10.dp)) {
                            specialist.certifications.forEach { certification ->
                                Row {
                                    Text(
                                        text = "• $certification",
                                        style = commonTextStyle,
                                        fontSize = 14.sp,
                                    )
                                }
                            }
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(25.dp))
                                .background(Color(0xff101010))
                                .height(40.dp)
                                .width(140.dp)
                                .clickable { SendEmail(context, useremail, specialist.email) }
                        ) {
                            Text(
                                text = "SEND REQUEST",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.inter))
                                ),
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
fun DetailOfSpecialistsPreview() {
    // Dữ liệu mẫu cho specialist
    val specialist = Specialist(
        specialist_id = 1,
        full_name = "Dr. Jane Cooper",
        age = 32,
        profession = "Licensed Professional Counselor (Psychotherapist)",
        years_of_experience = 6,
        location = "New York, NY, USA",
        rating = 3.67,
        review_count = 100,
        avatar = "https://example.com/avatar.jpg",
        specializations = listOf("Psychotherapy", "Cognitive Behavioral Therapy", "Counseling"),
        educations = listOf("PhD in Psychology", "Certified Therapist"),
        certifications = listOf("Licensed Counselor", "Certified CBT Therapist"),
        email = "janecooper@example.com"
    )

    // Hiển thị hộp thoại chi tiết specialist
    DetailOfSpecialists(
        specialist = specialist,
        useremail = "user@example.com",
        onDismiss = {}
    )
}
