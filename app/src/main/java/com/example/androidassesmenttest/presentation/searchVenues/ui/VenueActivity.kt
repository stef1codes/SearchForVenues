package com.example.androidassesmenttest.presentation.searchVenues.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidassesmenttest.presentation.venueDetails.ui.VenueDetailsActivity
import com.example.androidassesmenttest.usecases.usecasesImpl.ConnectionObservation
import com.example.androidassesmenttest.viewmodel.VenuesViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class VenueActivity : ComponentActivity() {

    private val venueViewModel: VenuesViewModel by viewModel()
    private val connectionObservation: ConnectionObservation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectionObservation.observe(this) {
            venueViewModel.changeInternetState(it)
        }

        setContent {
            VenuesScreen(
                viewModel = venueViewModel,
                navigation = ::navigate
            )
        }
    }

    private fun navigate(fsq_id: String) {
        val intent = Intent(this, VenueDetailsActivity::class.java)
        intent.putExtra("fsq_id", fsq_id)
        startActivity(intent)
    }
}
