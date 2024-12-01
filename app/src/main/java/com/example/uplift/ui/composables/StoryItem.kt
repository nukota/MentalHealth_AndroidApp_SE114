package com.example.uplift.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.example.uplift.data.models.Story

@Composable
fun StoryItem(modifier: Modifier = Modifier, story: Story, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .aspectRatio(33/38f)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = story.cover,
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

//@Preview
//@Composable
//fun StoryPreview() {
//    StoryItem(story = story(1, "A Short Story", "A short story about a cat", "John Doe", "https://firebasestorage.googleapis.com/v0/b/uplift-b0e90.appspot.com/o/the_happy_prince.svg?alt=media&token=f7e8012a-26ba-47ad-bfc6-d4183dbf1845", "This is the content of the story"))
//}
