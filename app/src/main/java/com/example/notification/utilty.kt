package com.example.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput

fun messagingWithReply(context: Context) {
    val activityIntent = Intent(context, MainActivity::class.java)
    val contentIntent = PendingIntent.getActivity(
        context,
        0, activityIntent,
        PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT  // setting the mutability flag
    )
    val remoteInput: RemoteInput = RemoteInput.Builder("key_text_reply")
        .setLabel("Your answer...")
        .build()
    val replyIntent: Intent
    var replyPendingIntent: PendingIntent? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        replyIntent = Intent(context, NotificationReceiver::class.java)
        replyPendingIntent = PendingIntent.getBroadcast(
            context,
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
    for (chatMessage in MainActivity.MESSAGES) {
        val notificationMessage = NotificationCompat.MessagingStyle.Message(
            chatMessage.text,
            chatMessage.timestamp,
            chatMessage.sender
        )
        messagingStyle.addMessage(notificationMessage)
    }
    val notification: Notification = NotificationCompat.Builder(context,
        MyApp.CHANNEL_ONE_ID
    )
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
    val notificationManager = NotificationManagerCompat.from(context)
    notificationManager.notify(4, notification)
}
