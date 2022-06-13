package jp.juggler.testfcmv1.utils

import android.util.Log

class LogTag(private val tag: String) {
    companion object {
        var appTag = "TestFcmV1"
    }

    fun e(ex: Throwable, message: String?) = Log.e(appTag, "$tag:$message", ex)
    fun w(ex: Throwable, message: String?) = Log.w(appTag, "$tag:$message", ex)

    fun e(message: String?) = Log.e(appTag, "$tag:$message")
    fun w(message: String?) = Log.w(appTag, "$tag:$message")
    fun i(message: String?) = Log.i(appTag, "$tag:$message")
    fun d(message: String?) = Log.d(appTag, "$tag:$message")
    fun v(message: String?) = Log.v(appTag, "$tag:$message")
}
