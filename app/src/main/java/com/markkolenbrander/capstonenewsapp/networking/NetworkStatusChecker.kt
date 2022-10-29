package com.markkolenbrander.capstonenewsapp.networking

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

interface NetworkStatusChecker {
    fun hasInternetConnection() : Boolean
}


class NetworkStatusCheckerImpl @Inject constructor (private val connectivityManager: ConnectivityManager?) : NetworkStatusChecker {


//    inline fun performIfConnectedToInternet(action: () -> Unit) {
//        if (hasInternetConnection()) {
//            action()
//        }
//    }

//    fun isConnectedToWifi(): Boolean {
//        val network = connectivityManager?.activeNetwork ?: return false
//        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
//
//        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
//    }

    override fun hasInternetConnection(): Boolean {
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }


}
