package uk.co.massimocarli.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

class LocaleChangedBroadcastReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {
    if (Intent.ACTION_LOCALE_CHANGED == intent.getAction()) {
      Log.d("LOCALE_CHANGE", "New Locale is: ${Locale.getDefault()}")
    }
  }
}
