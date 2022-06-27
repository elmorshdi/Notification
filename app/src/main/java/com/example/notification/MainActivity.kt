package com.example.notification

import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import androidx.appcompat.app.AppCompatActivity
import com.example.notification.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
     /*   mediaSession = MediaSessionCompat(this, "tag")

        MESSAGES.add(Message("Good morning!", "Mostafa"))
        MESSAGES.add(Message("Hello", null))
        MESSAGES.add(Message("Hi!", "Mostafa2"))
        binding.showNotificationButton.setOnClickListener {
            simple(this)
        }
        binding.bigTextNotificationButton.setOnClickListener {
            textExpanded(this)
        }
        binding.bigImageNotificationButton.setOnClickListener {
            bigImage(this)
        }
        binding.messagingWithReply.setOnClickListener {

            messagingWithReply(context = this)
        }
        binding.customNotification.setOnClickListener {
            showCustomNotification(this)
        }*/
    }


}


