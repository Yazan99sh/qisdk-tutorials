package ae.altkamul.papper.ui.idle

import ae.altkamul.papper.R
import android.os.Bundle
import android.util.Log
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.design.activity.RobotActivity

class IdleActivity : RobotActivity(), RobotLifecycleCallbacks {
    val TAG: String = "IDLE"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QiSDK.register(this, this)
        setContentView(R.layout.activity_idle)
    }

    override fun onRobotFocusGained(qiContext: QiContext?) {
        Log.i(TAG, "I seeeee YOU")
    }

    override fun onRobotFocusLost() {
        Log.i(TAG, "I Loooost You")
    }

    override fun onRobotFocusRefused(reason: String?) {

    }
}