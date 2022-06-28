package com.example.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.RemoteInput
import com.example.notification.ui.fragment.HomeFragment


class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.getStringExtra("toastMessage")!=null){
            val message = intent.getStringExtra("toastMessage")
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }


        val remoteInput: Bundle = RemoteInput.getResultsFromIntent(intent)
        val replyText = remoteInput.getCharSequence("key_text_reply")
        val answer = Message(replyText!!, null)
        HomeFragment.MESSAGES.add(answer)
    }
}
