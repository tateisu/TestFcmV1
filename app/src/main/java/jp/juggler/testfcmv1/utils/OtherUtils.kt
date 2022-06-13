package jp.juggler.testfcmv1.utils

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData

fun <T:Any?> MutableLiveData<T>.setOrPost(newValue:T){
    if( Looper.getMainLooper().thread.id == Thread.currentThread().id){
        value = newValue
    }else{
        postValue(newValue)
    }
}
