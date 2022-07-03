package com.example.mynotifications

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

fun simpleNotification(
    context: Context, title: String, body: String? = null,
    smallIcon: Int,
    Channel_Id: String,
    actionTitle: String? = null,
    actionIntent: PendingIntent? = null,
    contentIntent: PendingIntent? = null,
    largeIcon: Bitmap? = null,
    style: NotificationCompat.Style? = null
): NotificationCompat.Builder {

    val notification = NotificationCompat.Builder(context, Channel_Id)
        .setSmallIcon(smallIcon)
        .setContentTitle(title)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
        .setAutoCancel(true)

    if (body != null) {
        notification.setContentText(body)
    }
    if (contentIntent != null) {
        notification.setContentIntent(contentIntent)
    }
    if (actionTitle != null) {
        notification.addAction(smallIcon, actionTitle, actionIntent)
    }
    if (largeIcon != null) {
        notification.setLargeIcon(largeIcon)
    }
    if (style != null) {
        notification.setStyle(style)
    }

    return notification
}

fun customNotification(
    context: Context, title: String, body: String? = null,
    smallIcon: Int,
    Channel_Id: String,
    actionTitle: String? = null,
    actionIntent: PendingIntent? = null,
    contentIntent: PendingIntent? = null,
    largeIcon: Bitmap? = null,
    collapsedView: RemoteViews? = null,
    expandedView: RemoteViews? = null
): NotificationCompat.Builder {

    val notification = NotificationCompat.Builder(context, Channel_Id)
        .setSmallIcon(smallIcon)
        .setContentTitle(title)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
        .setAutoCancel(true)
        .setCustomBigContentView(expandedView)

    if (body != null) {
        notification.setContentText(body)
    }
    if (contentIntent != null) {
        notification.setContentIntent(contentIntent)
    }
    if (actionTitle != null) {
        notification.addAction(smallIcon, actionTitle, actionIntent)
    }
    if (largeIcon != null) {
        notification.setLargeIcon(largeIcon)
    }
    if (collapsedView != null) {
        notification.setCustomContentView(collapsedView)
    }
    if (expandedView != null) {
        notification.setCustomBigContentView(expandedView)
    }
    return notification
}

