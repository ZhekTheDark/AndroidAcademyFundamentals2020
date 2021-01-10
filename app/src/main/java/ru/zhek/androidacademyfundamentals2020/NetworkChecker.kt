package ru.zhek.androidacademyfundamentals2020

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log

object NetworkChecker {

    interface NetworkStateListener {
        fun onStateChanged() {}
    }

    var isConnected = true
    private val subscribers = mutableListOf<NetworkStateListener>()
//    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

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

//            if (connectivityManager != null) {
//                val builder = NetworkRequest.Builder()
//                connectivityManager.registerNetworkCallback(
//                    builder.build(),
//                    object : ConnectivityManager.NetworkCallback() {
//                        override fun onAvailable(network: Network) {
//                            subscribers.forEach { it.onStateChanged() }
//                            isConnected = true
//                            super.onAvailable(network)
//                        }
//
//                        override fun onLost(network: Network) {
//                            isConnected = false
//                            super.onLost(network)
//                        }
//
//                        override fun onUnavailable() {
//                            isConnected = false
//                            super.onUnavailable()
//                        }
//                    }
//                )
//            }
            connectivityManager.let {
                val builder = NetworkRequest.Builder()
                it.registerNetworkCallback(
                    builder.build(),
                    object : ConnectivityManager.NetworkCallback() {
                        override fun onAvailable(network: Network) {
                            subscribers.forEach { subscriber -> subscriber.onStateChanged() }
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

//    fun stopNetworkChecker(context: Context) {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        connectivityManager.unregisterNetworkCallback(networkCallback)
//    }
}
