package com.example.finalexam.services.notifications

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters

class ReminderWorker(private val context: Context, private val params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        NotificationHelper(context).createNotification()

        val workManager = WorkManager.getInstance(context)

        workManager.cancelAllWork()


        val myWorkRequest =
            OneTimeWorkRequestBuilder<ReminderWorker>()
                .setInitialDelay(600, java.util.concurrent.TimeUnit.SECONDS)
                .build()


        workManager.enqueue(myWorkRequest)

        return Result.success()
    }
}