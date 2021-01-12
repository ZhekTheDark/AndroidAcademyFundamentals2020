package ru.zhek.androidacademyfundamentals2020

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import kotlinx.coroutines.*

object NetworkChecker {

    interface NetworkStateListener {
        fun onNetworkStateChanged() {}
    }

    class NetworkCallback : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            isConnected = true
            subscribers.forEach { subscriber -> subscriber.onNetworkStateChanged() }
            super.onAvailable(network)
        }

        override fun onLost(network: Network) {
            isConnected = false
            super.onLost(network)
        }

        override fun onUnavailable() {
            isConnected = false
            super.onUnavailable()
        }
    }

    var isConnected = true
    private val subscribers = mutableListOf<NetworkStateListener>()
    private val networkCallback = NetworkCallback()

    fun addSubscriber(subscriber: NetworkStateListener) {
        if (subscriber !in subscribers)
            subscribers.add(subscriber)
    }

    fun removeSubscriber(subscriber: NetworkStateListener) {
        subscribers.remove(subscriber)
    }

    fun startNetworkChecker(context: Context) {
        try {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            connectivityManager.let {
                val builder = NetworkRequest.Builder()
                it.registerNetworkCallback(builder.build(), networkCallback)
            }

        } catch (e: Exception) {
            Log.d(this@NetworkChecker.toString(), "exception in NetworkChecker.startNetworkChecker")
        }
    }

    fun stopNetworkChecker(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}
