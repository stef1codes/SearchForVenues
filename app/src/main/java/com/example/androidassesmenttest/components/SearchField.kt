package com.example.androidassesmenttest.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchField(
    modifier: Modifier,
    value: String,
    valueChangeCallback: (String) -> Unit,
    keyboardActions: KeyboardActions,
    text: String,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = valueChangeCallback,
        label = {
            Text(
                fontWeight = FontWeight.Thin,
                text = text,
                color = Color.White
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = Color.White
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = keyboardActions
    )
}
