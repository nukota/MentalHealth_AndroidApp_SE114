package com.example.uplift.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.uplift.R

object NotificationHelper {

    fun showNotification(context: Context, title: String, message: String) {
        val channelId = "diary_channel"
        val channelName = "Diary Notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Channel for diary notifications"
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
            with(NotificationManagerCompat.from(context)) {
                notify(System.currentTimeMillis().toInt(), builder.build())
            }
        } else {
            // Handle the case where notifications are not enabled
            Log.w("NotificationHelper", "Notifications are not enabled")
        }
    }
}