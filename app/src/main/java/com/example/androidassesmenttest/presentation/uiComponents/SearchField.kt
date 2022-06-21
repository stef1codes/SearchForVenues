package com.example.androidassesmenttest.presentation.uiComponents

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions,
    text: String,
) {
    TextField(
        modifier = modifier,
        value = value,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = MaterialTheme.colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        onValueChange = onValueChange,
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
