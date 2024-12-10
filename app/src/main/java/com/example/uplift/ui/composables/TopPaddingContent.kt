package com.example.uplift.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Composable
fun TopPaddingContent(content: @Composable () -> Unit) {
    val view = LocalView.current
    val density = LocalDensity.current
    val insets = ViewCompat.getRootWindowInsets(view)
    val statusBarHeight = insets?.getInsets(WindowInsetsCompat.Type.statusBars())?.top ?: 0
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = with(density) { statusBarHeight.toDp() })
    ) {
        content()
    }
}