package com.example.finalexam.services.notifications

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.finalexam.MainActivity
import com.example.finalexam.R

class NotificationHelper(private val context: Context) {
    private val activityActionIntent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    @SuppressLint("UnspecifiedImmutableFlag")

    private val activityActionPendingIntent: PendingIntent =
        PendingIntent.getActivity(context, 0, activityActionIntent, 0)

    private val broadcastIntent = Intent(context, MyBroadcastReceiver::class.java).apply {
        putExtra("action_msg", "Toast Message")
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private val broadcastPendingIntent: PendingIntent =
        PendingIntent.getBroadcast(context, 0, broadcastIntent, 0)

    fun createNotification() {
        val lorem =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                    " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                    "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        @SuppressLint("UnspecifiedImmutableFlag")
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val notification = NotificationCompat.Builder(context, "default_notification_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("TITLE")
            .setContentText("Lorem ipsum")
            .setStyle(NotificationCompat.BigTextStyle().bigText(lorem))
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_launcher_foreground, "Open App", activityActionPendingIntent)
            .addAction(R.drawable.ic_launcher_background, "Stop Service", broadcastPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()
        NotificationManagerCompat.from(context).notify(1, notification)

    }

}