package com.example.androidassesmenttest.presentation.searchVenuesActivity.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidassesmenttest.domain.usecases.usecasesImpl.ConnectionObservation
import com.example.androidassesmenttest.presentation.searchVenuesActivity.viewmodel.VenuesViewModel
import com.example.androidassesmenttest.presentation.venueDetailsActivity.ui.VenueDetailsActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class VenueActivity : ComponentActivity() {

    private val venueViewModel: VenuesViewModel by viewModel()
    private val connectionObservation: ConnectionObservation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectionObservation.observe(this) {

            setContent {
                VenuesScreen(
                    viewModel = venueViewModel,
                    navigation = ::navigate,
                    internetState = it
                )
            }
        }
    }

    private fun navigate(fsq_id: String) {
        val intent = Intent(this, VenueDetailsActivity::class.java)
        intent.putExtra("fsq_id", fsq_id)
        startActivity(intent)
    }
}
