package com.example.uplift.ui.screens.specialists

import com.example.uplift.ui.composables.TopPaddingContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uplift.R
import com.example.uplift.data.models.Specialist
import com.example.uplift.ui.composables.SpecialistsBox
import com.example.uplift.ui.theme.Gray
import com.example.uplift.ui.theme.Routes
import com.example.uplift.viewmodels.AuthViewModel
import com.example.uplift.viewmodels.SpecialistsViewModel

@Composable
fun ListSpecialistsScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    specialistsViewModel: SpecialistsViewModel
) {
    val listSpecialists by specialistsViewModel.specialists.observeAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedSpecialist by remember { mutableStateOf<Specialist?>(null) }

    listSpecialists?.let {
        TopPaddingContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                Row() {
                    Column(
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                    ) {
                        Text(
                            text = "Explore", color = Gray, style = TextStyle(
                                fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.lemonada))
                            ), modifier = Modifier
                        )
                        Text(
                            text = "List Specialists", color = Color(0xff101010), style = TextStyle(
                                fontSize = 26.sp, fontFamily = FontFamily(Font(R.font.lemonada))
                            ), modifier = Modifier
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(40.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 28.dp, top = 28.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.menu),
                            contentDescription = null,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable {navController.navigate(Routes.SETTINGS) })
                    }
                }
                Spacer(modifier = Modifier.height(38.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LazyColumn(
                    ) {
                        items(it) { specialist ->
                            SpecialistsBox(iconAvartar = specialist.avatar,
                                textName = specialist.full_name,
                                textAge = specialist.age,
                                textProfession = specialist.profession,
                                textYoE = specialist.years_of_experience,
                                textLocation = specialist.location,
                                textRating = specialist.rating,
                                textReviewCount = specialist.review_count,
                                onClick = {
                                    selectedSpecialist = specialist
                                    showDialog = true
                                })
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                    if (showDialog && selectedSpecialist != null) {
                        DetailOfSpecialists(specialist = selectedSpecialist!!,
                            userEmail = authViewModel.getUserEmail()!!,
                            onDismiss = { showDialog = false })
                    }
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
//private fun ListSpecialistsScreenPreview() {
//    val sampleSpecialists = listOf(
//        Specialists(
//            specialist_id = 1,
//            full_name = "Dr. Jane Cooper",
//            age = 32,
//            profession = "Licensed Professional Counselor (Psychotherapist)",
//            years_of_experience = 6,
//            location = "New York, NY, USA",
//            rating = 3.67,
//            review_count = 100,
//            avartar = 1,
//            explore_spec_id = 2
//        ),
//        Specialists(
//            specialist_id = 2,
//            full_name = "Dr. Alexander",
//            age = 32,
//            profession = "Licensed Professional Counselor (Psychotherapist)",
//            years_of_experience = 6,
//            location = "New York, NY, USA",
//            rating = 3.67,
//            review_count = 100,
//            avartar = 2,
//            explore_spec_id = 2
//        ),
//        Specialists(
//            specialist_id = 3,
//            full_name = "Dr. Sarah Jones",
//            age = 28,
//            profession = "Clinical Psychologist",
//            years_of_experience = 5,
//            location = "Los Angeles, CA, USA",
//            rating = 4.5,
//            review_count = 200,
//            avartar = 3,
//            explore_spec_id = 2
//        )
//    )
//    ListSpecialistsScreen(listSpecialists = sampleSpecialists)
//}
