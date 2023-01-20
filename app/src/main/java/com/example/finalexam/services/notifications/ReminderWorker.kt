package com.example.finalexam.services.notifications

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class ReminderWorker(private val context: Context, private val params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        val title = "Reminder"
        val text = "You have not played game for a while."
        NotificationHelper(context).createNotification(title, text)

        val workManager = WorkManager.getInstance(context)

        workManager.cancelAllWork()

        val reminder = PeriodicWorkRequest.Builder(
            ReminderWorker::class.java,
            3,
            TimeUnit.HOURS,
            15,
            TimeUnit.MINUTES
        ).setInitialDelay(3, TimeUnit.HOURS).build()


        workManager.enqueueUniquePeriodicWork(
            "reminder_worker",
            ExistingPeriodicWorkPolicy.KEEP,
            reminder,
        )

        return Result.success()
    }
}