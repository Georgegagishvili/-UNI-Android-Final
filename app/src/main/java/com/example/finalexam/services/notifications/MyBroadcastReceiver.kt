package com.example.finalexam.services.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.work.WorkManager

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        WorkManager.getInstance().cancelAllWork()
        NotificationManagerCompat.from(context).cancelAll()
        Toast.makeText(context, "Reminder Service Stopped", Toast.LENGTH_SHORT).show()
    }
}