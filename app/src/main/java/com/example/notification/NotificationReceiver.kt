package com.example.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat


class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {

      if (intent!!.action == "BACK") {
          Toast.makeText(context, "Back clicked", Toast.LENGTH_LONG).show()
      }else if (intent.action == "FORWARD") {
          Toast.makeText(context, "Forward clicked", Toast.LENGTH_LONG).show()
      }else if (intent.action == "PLAY") {
          Toast.makeText(context, "Play clicked", Toast.LENGTH_LONG).show()
      }
    }
}
