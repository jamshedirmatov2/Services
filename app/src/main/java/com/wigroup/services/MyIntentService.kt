package com.wigroup.services

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class MyIntentService : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        println("onCreate")
        createNotificationChannel()
        startForeground(NOTIFY_ID, getNotification())
    }

    override fun onHandleIntent(intent: Intent?) {
        println("onHandleIntent")
        for (i in 0 until 5) {
            Thread.sleep(1000)
            println("Timer: $i")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT,
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun getNotification(): Notification =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

    companion object {

        const val CHANNEL_ID = "service"
        const val CHANNEL_NAME = "Service"
        const val NOTIFY_ID = 1
        const val NAME = "MyIntentService"

        fun newIntent(context: Context): Intent = Intent(context, MyIntentService::class.java)
    }
}