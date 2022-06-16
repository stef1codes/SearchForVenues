package com.example.androidassesmenttest.usecases.usecasesImpl

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class ConnectionObservation(context: Context) : LiveData<Boolean>() {

     var connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val validNetworks: MutableSet<Network> = HashSet()

    override fun onActive() {
        networkCallback = connectivityManagerCallback()
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onInactive() {
       // connectivityManager.unregisterNetworkCallback(connectivityManagerCallback())
    }

    private fun checkValidNetworks() {
        postValue(validNetworks.size > 0)
    }

    private fun connectivityManagerCallback() = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            val hasInternetCapability = networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
            if (hasInternetCapability == true) {
                    validNetworks.add(network)
                    checkValidNetworks()
                }
            }

        override fun onLost(network: Network) {
            super.onLost(network)
            validNetworks.remove(network)
            checkValidNetworks()

        }
    }
}