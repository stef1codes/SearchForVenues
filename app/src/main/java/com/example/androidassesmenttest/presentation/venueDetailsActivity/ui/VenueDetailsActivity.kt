package com.example.androidassesmenttest.presentation.venueDetailsActivity.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidassesmenttest.domain.usecases.usecasesImpl.ConnectionObservation
import com.example.androidassesmenttest.presentation.venueDetailsActivity.viewmodel.VenueDetailsViewModel
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

        val venueId: String = intent.getStringExtra("fsq_id").toString()

        venueDetailsViewModel.SetvenueDetailsId(venueId)

        connectionObservation.observe(this) {
            GlobalScope.launch { venueDetailsViewModel.getVenueDetail() }
            setContent {
                VenueDetailScreen(
                    viewModel = venueDetailsViewModel,
                    internetState = it
                )
            }
        }
    }
}
