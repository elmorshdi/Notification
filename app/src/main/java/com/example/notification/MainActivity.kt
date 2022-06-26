package com.example.notification

import android.R
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notification.MyApp.Companion.CHANNEL_ONE_ID
import com.example.notification.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationManager = NotificationManagerCompat.from(this);

        binding.showNotificationButton.setOnClickListener {
            simple()
        }
    }

    private fun simple() {
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ONE_ID)
            .setSmallIcon(R.drawable.ic_notification_overlay)
            .setContentTitle("My  Notification Title")
            .setContentText("My Notification Body")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .build()

        notificationManager.notify(1, notification)
    }

    /*@SuppressLint("RemoteViewLayout")
    private fun simpleNotification() {
        val channelID = "1000"
        val clickIntent = Intent(this, NotificationReceiver::class.java)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val contentView = RemoteViews(packageName, R.layout.music_notification)
        contentView.setTextViewText(R.id.tvTitle, "My Custom Notification Title")
        contentView.setTextViewText(R.id.tvDesc, "My Custom Notification Body")
        contentView.setOnClickPendingIntent(R.id.backButton,clickPendingIntent(clickIntent,"BACK"))
        contentView.setOnClickPendingIntent(R.id.forwardButton,clickPendingIntent(clickIntent,"FORWARD"))
        contentView.setOnClickPendingIntent(R.id.playButton,clickPendingIntent(clickIntent,"PLAY"))

        val builder = NotificationCompat.Builder(applicationContext,channelID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            // .setContentTitle("My Notification Title")
            //  .setContentText("My Notification Body")
            .setCustomContentView(contentView)

        builder.setContent(contentView)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID, "Custom Notification", NotificationManager.IMPORTANCE_DEFAULT)

            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(channelID)
        }
        val notification = builder.build()

        notificationManager.notify(1000, notification)
    }

    private fun clickPendingIntent(clickIntent: Intent, action:String): PendingIntent? {
        clickIntent.action = action
        return PendingIntent.getBroadcast(
            this,
            0, clickIntent, FLAG_IMMUTABLE
        )
    }
*//*
    private fun createNotif() {
        val id = "my_channel_id_01"
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel = manager.getNotificationChannel(id)
            if (channel == null) {
                channel =
                    NotificationChannel(id, "Channel Title", NotificationManager.IMPORTANCE_HIGH)
                //config nofication channel
                channel.description = "[Channel description]"
                channel.enableVibration(true)
                channel.vibrationPattern = longArrayOf(100, 1000, 200, 340)
                channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                manager.createNotificationChannel(channel)
            }
        }
        val notificationIntent = Intent(this, NoficationActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, id)
            .setSmallIcon(R.drawable.icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.bg))
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.bg))
                    .bigLargeIcon(null)
            )
            .setContentTitle("Title")
            .setContentText("Your text description")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(100, 1000, 200, 340))
            .setAutoCancel(false) //true touch on notificaiton menu dismissed, but swipe to dismiss
            .setTicker("Nofiication")
        builder.setContentIntent(contentIntent)
        val m = NotificationManagerCompat.from(applicationContext)
        //id to generate new notification in list notifications menu
        m.notify(Random().nextInt(), builder.build())
    }
*/
}