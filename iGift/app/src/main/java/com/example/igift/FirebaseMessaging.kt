package com.example.igift

import android.app.Notification
import android.app.NotificationChannel
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationManagerCompat

class FirebaseMessaging : FirebaseMessagingService() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        var title = remoteMessage.notification?.title
        var body = remoteMessage.notification?.body
        val CHANEL_ID = "HEADS_UP_NOTIFICATION"
        val channel=  NotificationChannel(
            CHANEL_ID,"MyNotification",
            NotificationManager.IMPORTANCE_HIGH
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = Notification.Builder(this, CHANEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)

        NotificationManagerCompat.from(this).notify(1,notification.build())

    }
}