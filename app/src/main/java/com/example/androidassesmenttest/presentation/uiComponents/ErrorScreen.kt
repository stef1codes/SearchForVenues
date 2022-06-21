package com.example.androidassesmenttest.presentation.uiComponents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun ErrorScreen(message: String?) {
    Text(
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.ExtraBold,
        text = message ?: "Something went wrong"
    )
}
