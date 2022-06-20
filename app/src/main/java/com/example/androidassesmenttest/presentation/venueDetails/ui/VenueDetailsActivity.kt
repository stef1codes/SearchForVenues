package com.example.androidassesmenttest.presentation.venueDetails.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidassesmenttest.presentation.venueDetails.viewmodel.VenueDetailsViewModel
import com.example.androidassesmenttest.usecases.usecasesImpl.ConnectionObservation
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(DelicateCoroutinesApi::class)
class VenueDetailsActivity : ComponentActivity() {

    private val connectionObservation: ConnectionObservation by inject()
    private val venueDetailsViewModel: VenueDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        venueDetailsViewModel.setVenueDetailId(intent.getStringExtra("fsq_id"))

        connectionObservation.observe(this) {
            GlobalScope.launch { venueDetailsViewModel.changeInternetState(it) }
        }

        GlobalScope.launch { venueDetailsViewModel.getVenueDetail() }

        setContent {
            VenueDetailScreen(viewModel = venueDetailsViewModel)
        }
    }
}
