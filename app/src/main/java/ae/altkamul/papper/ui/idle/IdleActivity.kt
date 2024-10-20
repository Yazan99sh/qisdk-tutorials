package ae.altkamul.papper.ui.idle

import ae.altkamul.papper.R
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.VideoView
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.humanawareness.HumanAwareness
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy

class IdleActivity : RobotActivity(), RobotLifecycleCallbacks {
    val TAG: String = "IDLE"
    private var humanAwareness: HumanAwareness? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QiSDK.register(this, this)
        supportActionBar?.hide()
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.OVERLAY)
        setContentView(R.layout.activity_idle)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val videoView: VideoView = findViewById(R.id.idle_video)
        val uriVideo : String = "android.resource://" + packageName + "/raw/" + R.raw.a
        videoView.setVideoURI(Uri.parse(uriVideo))
        videoView.setOnCompletionListener {
            videoView.start() // Restart the video when it completes
        }
        videoView.start()
    }

    override fun onRobotFocusGained(qiContext: QiContext?) {
        humanAwareness = qiContext!!.humanAwareness
        humanAwareness!!.addOnHumansAroundChangedListener {

        }
    }

    override fun onRobotFocusLost() {
        humanAwareness!!.removeAllOnHumansAroundChangedListeners()
    }

    override fun onRobotFocusRefused(reason: String?) {
        Log.i(TAG, "$reason")

    }
    override fun onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this)
        super.onDestroy()
    }
}