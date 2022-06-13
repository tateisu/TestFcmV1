package jp.juggler.testfcmv1.fcm

import android.os.Build
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import jp.juggler.testfcmv1.MainActivity
import jp.juggler.testfcmv1.R
import jp.juggler.testfcmv1.utils.LogTag
import jp.juggler.testfcmv1.utils.setOrPost
import jp.juggler.testfcmv1.utils.showToast

class MyFirebaseMessagingService : FirebaseMessagingService() {
    companion object {
        private val log = LogTag("MyFirebaseMessagingService")

        private const val KEY_TITLE = "title"
        private const val KEY_DESC = "text"

        private const val NOTIFICATION_ID = 1
        private const val NOTIFICATION_CHANNEL_ID = "notificationA"
    }

    override fun onNewToken(token: String) {
        log.i("onNewToken $token")
        showToast(false, "onNewToken $token")
        MainActivity.token.setOrPost(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        log.i("onMessageReceived ${message.messageId}")
        showNotification(
            messageId = message.messageId ?: "(no id)",
            title = message.data[KEY_TITLE] ?: "(no title)",
            message = message.data[KEY_DESC] ?: "(no desc)",
        )
    }

    private fun showNotification(
        messageId: String,
        title: String,
        message: String
    ) {
        val manager = NotificationManagerCompat.from(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannelCompat.Builder(
                NOTIFICATION_CHANNEL_ID,
                NotificationManagerCompat.IMPORTANCE_DEFAULT
            )
                .setName("プッシュ通知")
                .setDescription("プッシュメッセージ由来の通知")
                .build()
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification1)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        manager.notify(messageId, NOTIFICATION_ID, notification)
    }
}
