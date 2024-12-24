package com.example.uplift.ui.screens.specialists

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.rememberAsyncImagePainter
import com.example.uplift.R
import com.example.uplift.data.models.Specialist
import com.example.uplift.ui.composables.sendEmail

@Composable
fun DetailOfSpecialists(
    specialist: Specialist,
    userEmail: String,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val commonTextStyle = TextStyle(
        color = Color(0xFF505050),
        fontFamily = FontFamily(Font(R.font.inter)),
        fontSize = 13.sp
    )
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
    ) {
        Surface(
            modifier = Modifier
                .size(width = 650.dp, height = 560.dp)
                .padding(16.dp),
            color = Color.White,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            LazyColumn {
                item {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = specialist.full_name,
                            color = Color(0xFF007178),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        IconButton(onClick = onDismiss) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color(0xFF007178)
                            )
                        }
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(132.dp)
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
                                    .clip(CircleShape)
                                    .padding(14.dp)
                                    .size(60.dp)
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(top = 28.dp, bottom = 14.dp)
                        ) {
                            Row {
                                Text(
                                    text = "Age: ",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp,
                                    style = commonTextStyle
                                )
                                Text(
                                    fontSize = 13.sp,
                                    text = specialist.age.toString(),
                                    style = commonTextStyle,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Row {
                                Text(
                                    fontSize = 12.sp,
                                    text = "Profession: ",
                                    fontWeight = FontWeight.SemiBold,
                                    style = commonTextStyle
                                )
                                Text(
                                    fontSize = 12.sp,
                                    text = specialist.profession,
                                    style = commonTextStyle,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Row(
                            ) {
                                Text(
                                    fontSize = 12.sp,
                                    text = "Year of Experience: ",
                                    fontWeight = FontWeight.SemiBold,
                                    style = commonTextStyle
                                )
                                Text(
                                    text = specialist.years_of_experience.toString(),
                                    fontSize = 12.sp,
                                    style = commonTextStyle,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Row(
                            ) {
                                Text(
                                    fontSize = 12.sp,
                                    text = "Location: ",
                                    fontWeight = FontWeight.SemiBold,
                                    style = commonTextStyle
                                )
                                Text(
                                    fontSize = 12.sp,
                                    text = specialist.location,
                                    color = Color(0xFF505050),
                                    style = commonTextStyle,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(17.dp)
                                    .padding(end = 20.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 4.dp)
                                        .fillMaxHeight()
                                        .size(14.dp)
                                )
                                Text(
                                    text = specialist.rating.toString() + "(" + specialist.review_count.toString() + "reviews)",
                                    color = Color(0xFF787300),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
                item {
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text(
                            text = "Specializations: ",
                            fontWeight = FontWeight.SemiBold,
                            style = commonTextStyle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                        )
                        specialist.specializations.forEach { specialization ->
                            Row {
                                Text(
                                    text = "• $specialization",
                                    style = commonTextStyle,
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        Text(
                            text = "Educations: ",
                            fontWeight = FontWeight.SemiBold,
                            style = commonTextStyle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                        )
                        specialist.educations.forEach { education ->
                            Row {
                                Text(
                                    text = "• $education",
                                    style = commonTextStyle,
                                    fontSize = 12.sp,
                                )
                            }
                        }
                        Text(
                            text = "Certifications: ",
                            fontWeight = FontWeight.SemiBold,
                            style = commonTextStyle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                        )
                        specialist.certifications.forEach { certification ->
                            Row {
                                Text(
                                    text = "• $certification",
                                    style = commonTextStyle,
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    }
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(18.dp))
                                .background(Color(0xff101010))
                                .height(36.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .clickable { sendEmail(context, userEmail, specialist.email) }
                        ) {
                            Text(
                                text = "SEND REQUEST",
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontFamily = FontFamily(Font(R.font.inter))
                                ),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}
