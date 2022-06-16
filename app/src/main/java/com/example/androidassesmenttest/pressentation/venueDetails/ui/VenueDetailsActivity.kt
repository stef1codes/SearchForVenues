package com.example.androidassesmenttest.pressentation.venueDetails.ui

import android.content.Context
import android.net.*
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.LiveData
import com.example.androidassesmenttest.pressentation.venueDetails.viewmodel.VenueDetailsViewModel
import com.example.androidassesmenttest.usecases.usecasesImpl.ConnectionObservation
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


@OptIn(DelicateCoroutinesApi::class)
class VenueDetailsActivity : ComponentActivity() {
    private val connectionObservation: ConnectionObservation by inject()
    val venueDetailsViewModel: VenueDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        if ((this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo == null) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }*/
        venueDetailsViewModel.setVenueDetailId(intent.getStringExtra("fsq_id"))

        connectionObservation.observe(this) {
            GlobalScope.launch {
                venueDetailsViewModel.changeInternetState(it)
            }
        }

        GlobalScope.launch {
            venueDetailsViewModel.getVenueDetail()
        }


        setContent {
            VenueDetailScreen(viewModel = venueDetailsViewModel)
        }
    }

}