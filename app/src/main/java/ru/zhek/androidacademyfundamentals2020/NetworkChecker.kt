package ru.zhek.androidacademyfundamentals2020

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log

object NetworkChecker {

    interface Listener {
        fun onStateChanged() {}
    }

    var isConnected = true
    private val subscribers = mutableListOf<Listener>()

    fun addSubscriber(subscriber: Listener) {
        subscribers.add(subscriber)
    }

    fun removeSubscriber(subscriber: Listener) {
        subscribers.remove(subscriber)
    }

    fun start(context: Context) {
        try {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val builder = NetworkRequest.Builder()

            if (connectivityManager != null) {
                connectivityManager.registerNetworkCallback(
                    builder.build(),
                    object : ConnectivityManager.NetworkCallback() {
                        override fun onAvailable(network: Network) {
                            subscribers.forEach { it.onStateChanged() }
                            isConnected = true
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
                )
            }
        } catch (e: Exception) {
            Log.d(this.toString(), "exception in NetworkChecker.start")
        }
    }
}