package com.example.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import com.example.notification.MyApp.Companion.CHANNEL_ONE_ID
import com.example.notification.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notificationManager: NotificationManagerCompat
    private var mediaSession: MediaSessionCompat? = null
companion object{
  M
    fun messagingWithReplyCompanion() {
        TODO("Not yet implemented")
    }

    var MESSAGES :ArrayList<Message> = ArrayList()

}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationManager = NotificationManagerCompat.from(this);
        mediaSession = MediaSessionCompat(this, "tag")

        MESSAGES.add(Message("Good morning!", "Jim"))
        MESSAGES.add(Message("Hello", null))
        MESSAGES.add(Message("Hi!", "Jenny"))
        binding.showNotificationButton.setOnClickListener {
            simple()
        }
        binding.bigTextNotificationButton.setOnClickListener {
            textExpanded()
        }
        binding.bigImageNotificationButton.setOnClickListener {
            bigImage()
        }
        binding.messagingWithReply.setOnClickListener {

            messagingWithReply()
        }
    }




    private fun simple() {
        val activityIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this,
            0, activityIntent,
            PendingIntent.FLAG_IMMUTABLE  // setting the mutability flag

        )

        val broadcastIntent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra("toastMessage", "message")
        val actionIntent = PendingIntent.getBroadcast(
            this,
            0, broadcastIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT // setting the mutability flag
        )

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ONE_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications)
            .setContentTitle("My  Notification Title")
            .setContentText("My Notification Body")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
             .setColor(Color.BLUE)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .addAction(R.drawable.ic_baseline_music_note_24, "Toast", actionIntent)
            .build()
        notificationManager.notify(1, notification)
    }
    private fun textExpanded() {
        val activityIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this,
            0, activityIntent,
            PendingIntent.FLAG_IMMUTABLE  // setting the mutability flag

        )
        val largeIcon = BitmapFactory.decodeResource(resources, com.example.notification.R.drawable.download)

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ONE_ID)
            .setSmallIcon(com.example.notification.R.drawable.ic_baseline_notifications)
            .setContentTitle("My  Notification Title")
            .setContentText("My Notification Body")
            .setLargeIcon(largeIcon)
            .setStyle( NotificationCompat.BigTextStyle()
                .bigText(getString(R.string.long_dummy_text))
                .setBigContentTitle("Big Content Title")
                .setSummaryText("Summary Text"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.BLUE)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
              .build()
        notificationManager.notify(2, notification)
    }
    private fun bigImage(){
        val activityIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this,
            0, activityIntent,
            PendingIntent.FLAG_IMMUTABLE  // setting the mutability flag

        )

        val picture = BitmapFactory.decodeResource(resources, R.drawable.download)

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ONE_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications)
            .setContentTitle("My  Notification Title")
            .setContentText("My Notification Body")
            .setLargeIcon(picture)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(picture)
                    .bigLargeIcon(null)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .build()

        notificationManager.notify(3, notification)
    }

     fun messagingWithReply() {
        val activityIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this,
            0, activityIntent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT  // setting the mutability flag
        )
        val remoteInput: RemoteInput = RemoteInput.Builder("key_text_reply")
            .setLabel("Your answer...")
            .build()
        val replyIntent: Intent
        var replyPendingIntent: PendingIntent? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            replyIntent = Intent(this, NotificationReceiver::class.java)
            replyPendingIntent = PendingIntent.getBroadcast(
                this,
                0, replyIntent,
                PendingIntent.FLAG_MUTABLE // setting the mutability flag
            )
        } else {
            //start chat activity instead (PendingIntent.getActivity)
            //cancel notification with notificationManagerCompat.cancel(id)
        }
        val replyAction: NotificationCompat.Action = NotificationCompat.Action.Builder(
            com.example.notification.R.drawable.ic_baseline_notifications,
            "Reply",
            replyPendingIntent
        ).addRemoteInput(remoteInput).build()
        val messagingStyle = NotificationCompat.MessagingStyle("Me")
        messagingStyle.conversationTitle = "Group Chat"
        for (chatMessage in MESSAGES) {
            val notificationMessage = NotificationCompat.MessagingStyle.Message(
                chatMessage.text,
                chatMessage.timestamp,
                chatMessage.sender
            )
            messagingStyle.addMessage(notificationMessage)
        }
        val notification: Notification = NotificationCompat.Builder(applicationContext, CHANNEL_ONE_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications)
            .setStyle(messagingStyle)
            .addAction(replyAction)
            .setColor(Color.BLUE)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .build()
        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.notify(4, notification)
    }
    }


