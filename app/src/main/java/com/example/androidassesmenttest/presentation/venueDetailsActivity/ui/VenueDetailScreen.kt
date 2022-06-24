package com.example.androidassesmenttest.presentation.venueDetailsActivity.ui

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
import com.example.androidassesmenttest.data.local.Entity.CategoryEntity
import com.example.androidassesmenttest.data.local.Entity.VenueDetailEntity
import com.example.androidassesmenttest.presentation.ui.InternetStateMessage
import com.example.androidassesmenttest.presentation.uiComponents.ErrorScreen
import com.example.androidassesmenttest.presentation.uiComponents.InitialScreen
import com.example.androidassesmenttest.presentation.uiComponents.LoadingScreen
import com.example.androidassesmenttest.presentation.venueDetailsActivity.state.VenueDetailState
import com.example.androidassesmenttest.presentation.venueDetailsActivity.viewmodel.VenueDetailsViewModel
import com.example.androidassesmenttest.util.Constants.TITLE_DETAILSCREEN

@Composable
fun VenueDetailScreen(viewModel: VenueDetailsViewModel, internetState: Boolean) {
    val state = viewModel.venueDetailsState.collectAsState()
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            if (!internetState) {
                InternetStateMessage(internetState)
            }
            when (val venueDetail = state.value) {
                VenueDetailState.Empty -> InitialScreen(TITLE_DETAILSCREEN)
                is VenueDetailState.Error -> ErrorScreen(venueDetail.errorMessage)
                VenueDetailState.Loading -> LoadingScreen()
                is VenueDetailState.Success -> SuccesScreen(venueDetail.category, venueDetail.venueDetail)
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
