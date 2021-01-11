package ru.zhek.androidacademyfundamentals2020

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//TODO
object NetworkChecker {

    interface NetworkStateListener {
        fun onNetworkStateChanged() {}
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
        //TODO
        CoroutineScope(Dispatchers.Main).launch {
            var i = 0
            while (i > -1) {
                delay(1_000)

                Log.d("myTag NetworkChecker", i.toString())
                i++
            }
        }

        try {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            connectivityManager.let {
                val builder = NetworkRequest.Builder()
                it.registerNetworkCallback(
                    builder.build(),
                    object : ConnectivityManager.NetworkCallback() {
                        override fun onAvailable(network: Network) {
                            Log.d(this@NetworkChecker.toString(), "onAvailable called")
                            isConnected = true
                            subscribers.forEach { subscriber -> subscriber.onNetworkStateChanged() }
                            super.onAvailable(network)
                        }

                        override fun onLost(network: Network) {
                            Log.d(this@NetworkChecker.toString(), "onLost called")
                            isConnected = false
                            super.onLost(network)
                        }

                        override fun onUnavailable() {
                            Log.d(this@NetworkChecker.toString(), "onUnavailable called")
                            isConnected = false
                            super.onUnavailable()
                        }
                    }
                )
            }

        } catch (e: Exception) {
            Log.d(this@NetworkChecker.toString(), "exception in NetworkChecker.start")
        }
    }

//    fun stopNetworkChecker(context: Context) {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        connectivityManager.unregisterNetworkCallback(networkCallback)
//    }
}
