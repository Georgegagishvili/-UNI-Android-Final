package com.example.finalexam
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.finalexam.databinding.ActivityMainBinding
import com.example.finalexam.services.api.RestClient
import com.example.finalexam.services.notifications.ReminderWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun init() {
        RestClient.getRetrofit()
        createNotificationChannel()
    }

    override fun onStop() {
        super.onStop()
        createWorkRequest()
    }

    override fun onDestroy() {
        super.onDestroy()
        createWorkRequest()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification_channel)
            val descriptionText = "Description Text"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel("default_notification_channel", name, importance).apply {
                    description = descriptionText
                }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createWorkRequest() {
        val myWorkRequest =
            OneTimeWorkRequestBuilder<ReminderWorker>()
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(myWorkRequest)
    }
}
