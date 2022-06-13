package jp.juggler.testfcmv1

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.google.firebase.messaging.FirebaseMessaging
import jp.juggler.testfcmv1.databinding.ActivityMainBinding
import jp.juggler.testfcmv1.utils.LogTag
import jp.juggler.testfcmv1.utils.setOrPost
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : AppCompatActivity() {
    companion object{
        private val log = LogTag("MainActivity")
        val token = MutableLiveData<String>()
    }

    private val views by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        token.observe(this){ views.etToken.setText( it?:"") }

        views.btnCopy.setOnClickListener {
            ContextCompat.getSystemService(this, ClipboardManager::class.java)
            ?.setPrimaryClip(ClipData.newPlainText("token",views.etToken.text?.toString() ?: ""))
        }

        loadToken()
    }

    private fun loadToken(){
        lifecycleScope.launch {
            try {
                token.setOrPost(FirebaseMessaging.getInstance().token.await())
            } catch (ex: Throwable) {
                log.e(ex, "loadToken failed")
            }
        }
    }
}
