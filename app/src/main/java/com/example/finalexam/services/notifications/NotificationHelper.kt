package com.example.finalexam.services.notifications

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.finalexam.R

class NotificationHelper(private val context: Context) {
    @SuppressLint("UnspecifiedImmutableFlag")
    private val activityActionPendingIntent: PendingIntent =
        NavDeepLinkBuilder(context)
            .setGraph(R.navigation.nav_graph).setDestination(R.id.splashFragment)
            .createPendingIntent()

    private val broadcastIntent = Intent(context, MyBroadcastReceiver::class.java).apply {
        putExtra("action_msg", "Toast Message")
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private val broadcastPendingIntent: PendingIntent =
        PendingIntent.getBroadcast(context, 0, broadcastIntent, 0)

    fun createNotification(title: String, text: String) {
        val notification = NotificationCompat.Builder(context, "default_notification_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(text))
            .setContentIntent(activityActionPendingIntent)
            .addAction(R.drawable.ic_launcher_foreground,
                "Open application",
                activityActionPendingIntent)
            .addAction(R.drawable.ic_launcher_background,
                "Don't remind again",
                broadcastPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()
        NotificationManagerCompat.from(context).notify(1, notification)
    }
}