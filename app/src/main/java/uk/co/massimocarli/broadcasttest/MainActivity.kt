package uk.co.massimocarli.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

  lateinit var connectivityReceiver: ConnectivityChangeBroadcastReceiver

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    connectivityReceiver = ConnectivityChangeBroadcastReceiver()
    registerReceiver(
      connectivityReceiver,
      IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    )
  }

  override fun onDestroy() {
    unregisterReceiver(connectivityReceiver)
    super.onDestroy()
  }

  class ConnectivityChangeBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      intent?.run {
        val networkType = getIntExtra(ConnectivityManager.EXTRA_NETWORK_TYPE, 0)
        Toast.makeText(
          context,
          "Network-Type: ${getTypeName(networkType)}",
          Toast.LENGTH_SHORT
        ).show()
      }
    }

    private fun getTypeName(type: Int): String =
      when (type) {
        ConnectivityManager.TYPE_BLUETOOTH -> "BLUETOOTH"
        ConnectivityManager.TYPE_DUMMY -> "DUMMY"
        ConnectivityManager.TYPE_ETHERNET -> "ETHERNET"
        ConnectivityManager.TYPE_MOBILE -> "MOBILE"
        ConnectivityManager.TYPE_VPN -> "VPN"
        ConnectivityManager.TYPE_WIFI -> "WIFI"
        else -> "Others"
      }
  }
}
