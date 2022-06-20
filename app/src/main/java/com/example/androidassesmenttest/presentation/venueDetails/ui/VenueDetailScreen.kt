package com.example.androidassesmenttest.presentation.venueDetails.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.androidassesmenttest.R
import com.example.androidassesmenttest.components.ErrorScreen
import com.example.androidassesmenttest.components.InitialScreen
import com.example.androidassesmenttest.components.LoadingScreen
import com.example.androidassesmenttest.data.local.Entity.CategoryEntity
import com.example.androidassesmenttest.data.local.Entity.VenueDetailEntity
import com.example.androidassesmenttest.presentation.venueDetails.state.VenueDetailState
import com.example.androidassesmenttest.presentation.venueDetails.viewmodel.VenueDetailsViewModel

@Composable
fun VenueDetailScreen(viewModel: VenueDetailsViewModel) {
    val state = viewModel.venueDetailsstate.collectAsState()
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            if (viewModel.hasInternet.value == false) {
                Snackbar(action = {}) {
                    Text(text = "no connection, please connect to an internet service")
                }
            }
            when (val _state = state.value) {
                VenueDetailState.Empty -> InitialScreen("Venue detail screen")
                is VenueDetailState.Error -> ErrorScreen(_state.errorMessage)
                VenueDetailState.Loading -> LoadingScreen()
                is VenueDetailState.Success -> SuccesScreen(_state.category, _state.venueDetail)
            }
        }
    }
}

@Composable
fun SuccesScreen(category: List<CategoryEntity>, venueDetail: VenueDetailEntity) {
    if (!category.isNullOrEmpty()) {
        Image(
            painter = rememberAsyncImagePainter(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
    } else {
        Canvas(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
        ) {
            drawRect(
                color = Color.Gray,
            )
        }
    }

    Text(
        text = venueDetail.name ?: "",
        style = TextStyle(fontSize = 30.sp),
        fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary
    )
    Text(text = "address: ${venueDetail.location?.address} ")
    Text(text = "country: ${venueDetail.location?.country} ")
    Text(text = "postcode: ${venueDetail.location?.postcode} ")
    Divider(thickness = 1.dp, color = Color.Gray)
    Text(
        text = "Categories",
        style = TextStyle(fontSize = 20.sp),
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        items(items = category, itemContent = { category ->
            Card(elevation = 2.dp, modifier = Modifier.padding(vertical = 8.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "name: ${category.name}",
                        color = MaterialTheme.colors.primaryVariant
                    )
                    Text(
                        text = "id: ${category.categoryId}",
                        color = MaterialTheme.colors.primaryVariant
                    )
                    Text(
                        text = "prefix: ${category.prefix}",
                        color = MaterialTheme.colors.primaryVariant
                    )
                }
            }
        })
    }
}
