package com.example.androidassesmenttest.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidassesmenttest.util.Constants

@Composable
internal fun InternetStateMessage(internetState: Boolean) {
    if (!internetState) {
        Snackbar(action = {}, modifier = Modifier.fillMaxWidth()) {
        Text(text = Constants.NO_CONNECTION)
    }
    }
}
