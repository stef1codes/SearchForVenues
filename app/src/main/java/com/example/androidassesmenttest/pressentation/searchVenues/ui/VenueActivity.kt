package com.example.androidassesmenttest.pressentation.searchVenues.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.androidassesmenttest.pressentation.searchVenues.viewmodel.VenuesViewModel
import com.example.androidassesmenttest.pressentation.venueDetails.ui.VenueDetailsActivity
import com.example.androidassesmenttest.usecases.usecasesImpl.ConnectionObservation
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
                navigation = ::navigate)
        }
    }

    private fun navigate(fsq_id: String) {
        val intent = Intent(this, VenueDetailsActivity::class.java)
        intent.putExtra("fsq_id", fsq_id)
        startActivity(intent)
    }
}
