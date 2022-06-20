package com.example.androidassesmenttest.presentation.searchVenues.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.androidassesmenttest.components.ErrorScreen
import com.example.androidassesmenttest.components.InitialScreen
import com.example.androidassesmenttest.components.LoadingScreen
import com.example.androidassesmenttest.components.SearchField
import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.presentation.searchVenues.state.VenuesState
import com.example.androidassesmenttest.viewmodel.VenuesViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction1

@Composable
fun VenuesScreen(viewModel: VenuesViewModel, navigation: KFunction1<String, Unit>) {

    val state = viewModel.venueState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchToolbar(viewModel)
        VenuesScreenState(state, viewModel, navigation)
    }
}

@Composable
fun VenuesScreenState(
    state: State<VenuesState>,
    vm: VenuesViewModel,
    navigation: (String) -> Unit,
) {

    if (vm.hasInternet.value == false) {
        Snackbar(action = {}) {
            Text(text = "not connection, please connect to an internet service")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                PaddingValues(horizontal = 8.dp)
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (val venueData = state.value) {
            is VenuesState.Error -> ErrorScreen(message = venueData.errorMessage)
            is VenuesState.Loading -> LoadingScreen()
            is VenuesState.Success -> SuccessScreen(venueData.venues, vm, navigation)
            is VenuesState.Empty -> InitialScreen("Venue screen")
        }
    }
}

@Composable
fun SearchToolbar(viewModel: VenuesViewModel) {

    val near = viewModel.near.value
    val limit = viewModel.limit.value
    val radius = viewModel.radius.value

    val coroutineScope = rememberCoroutineScope()
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.primary
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                SearchField(
                    modifier = Modifier.fillMaxWidth(),
                    value = near?:"",
                    valueChangeCallback = {
                        viewModel.setNearQuery(it)
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (viewModel.hasInternet.value == true) {
                                coroutineScope.launch { viewModel.getVenues() }
                            }
                        },
                    ),
                    text = "near",
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                SearchField(
                    text = "radius",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(start = 16.dp, bottom = 16.dp),
                    value = radius?:"",
                    valueChangeCallback = {
                        viewModel.setRadiusQuery(it)
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = { coroutineScope.launch { viewModel.getVenues() } },
                    )
                )
                SearchField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 16.dp, end = 16.dp),

                    value = limit?.toString() ?: "",
                    valueChangeCallback = { viewModel.setLimitQuery(it) },
                    keyboardActions = KeyboardActions(
                        onSearch = { coroutineScope.launch { viewModel.getVenues() } },

                    ),
                    text = "limit",
                )
            }
        }
    }
}

@Composable
fun SuccessScreen(data: List<VenueEntity>?, viewModel: VenuesViewModel, navigation: (String) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(
            items = data ?: emptyList(),
            itemContent = { venue ->
                Row(
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                if (viewModel.hasInternet.value == true) {
                                    navigation(venue.fsqId)
                                }
                            }
                        }
                        .fillMaxWidth()
                        .padding(2.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.surface),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = venue.name ?: "",
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(text = "adress: ${venue.location?.address}, ${venue.location?.postcode}")
                        Text(text = "region:${venue.location?.region}")
                        Text(text = "locality:${venue.location?.locality}")
                        Text(text = "dma:${venue.location?.dma}")
                        Text(text = "country:${venue.location?.country}")
                        Text(text = "meter: ${venue.distance}")
                    }
                }
            }
        )
    }
}
