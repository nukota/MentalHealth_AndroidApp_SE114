package com.example.uplift.ui.composables


fun getInitials(input: String): String {
    return input
        .split(" ")
        .filter { it.isNotEmpty() }
        .joinToString("") { it[0].uppercase() }
}
