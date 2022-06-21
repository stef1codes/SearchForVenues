package com.example.androidassesmenttest.presentation.searchVenuesActivity.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.presentation.searchVenuesActivity.state.VenuesState
import com.example.androidassesmenttest.presentation.searchVenuesActivity.viewmodel.VenuesViewModel
import com.example.androidassesmenttest.presentation.ui.InternetStateMessage
import com.example.androidassesmenttest.presentation.uiComponents.ErrorScreen
import com.example.androidassesmenttest.presentation.uiComponents.InitialScreen
import com.example.androidassesmenttest.presentation.uiComponents.LoadingScreen
import com.example.androidassesmenttest.presentation.uiComponents.SearchField
import com.example.androidassesmenttest.util.Constants.DEFAULT_WELCOME_MSG
import com.example.androidassesmenttest.util.Constants.LIMIT
import com.example.androidassesmenttest.util.Constants.NEAR
import com.example.androidassesmenttest.util.Constants.RADIUS
import com.example.androidassesmenttest.util.Constants.TITLE_APP
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction1

@Composable
fun VenuesScreen(
    viewModel: VenuesViewModel,
    navigation: KFunction1<String, Unit>,
    internetState: Boolean,
) {

    val state = viewModel.venueState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        InternetStateMessage(internetState)
        SearchToolbar(viewModel)
        VenuesScreenState(state, viewModel, navigation, internetState)
    }
}

@Composable
fun VenuesScreenState(
    state: State<VenuesState>,
    vm: VenuesViewModel,
    navigation: (String) -> Unit,
    internetState: Boolean
) {
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
            is VenuesState.Success -> SuccessScreen(venueData.venues, navigation, internetState)
            is VenuesState.Empty -> InitialScreen(venueData.message ?: DEFAULT_WELCOME_MSG)
        }
    }
}

@Composable
fun SearchToolbar(viewModel: VenuesViewModel) {
    var near by rememberSaveable { mutableStateOf("") }
    var limit by rememberSaveable { mutableStateOf("") }
    var radius by rememberSaveable { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.primary
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                letterSpacing = 2.sp,
                text = TITLE_APP,
                color = MaterialTheme.colors.surface,
                fontWeight = FontWeight.ExtraBold,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Cursive,
                    shadow = Shadow(
                        Color.Black
                    )
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                SearchField(
                    modifier = Modifier.fillMaxWidth(),
                    value = near,
                    onValueChange = { newValue ->
                        near = newValue
                            .replace("[^A-Za-z0-9 ]".toRegex(), "")
                            .replace("[0-9]".toRegex(), "")
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = { viewModel.getVenues(near, limit, radius) },
                    ),
                    text = NEAR,
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                SearchField(
                    text = RADIUS,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(start = 16.dp, bottom = 16.dp),
                    value = radius,
                    onValueChange = { newValue -> radius = newValue.filter { it.isDigit() } },
                    keyboardActions = KeyboardActions(
                        onSearch = { viewModel.getVenues(near, limit, radius) },
                    )
                )

                SearchField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
                    value = limit,
                    onValueChange = { newValue -> limit = newValue.filter { it.isDigit() } },
                    keyboardActions = KeyboardActions(
                        onSearch = { coroutineScope.launch { viewModel.getVenues(near, limit, radius) } },
                    ),
                    text = LIMIT,
                )
            }
        }
    }
}

@Composable
fun SuccessScreen(
    data: List<VenueEntity>?,
    navigation: (String) -> Unit,
    internetState: Boolean,
) {
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
                                if (internetState == true) {
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
                            fontWeight = FontWeight.ExtraBold,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Cursive,
                                shadow = Shadow(
                                    color = Color.Black,
                                    offset = Offset(0.2f, 0.2f)
                                )
                            )
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
