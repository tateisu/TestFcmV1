package jp.juggler.testfcmv1.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

val mainHandler = Handler(Looper.getMainLooper())

fun Context.showToast(longDuration:Boolean, message:String){
    mainHandler.post{
        val duration = when(longDuration){
            true -> Toast.LENGTH_LONG
            else -> Toast.LENGTH_SHORT
        }
        Toast.makeText(this,message,duration).show()
    }
}
