/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.main_menu

import androidx.annotation.StringRes
import android.util.Log

import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.builder.SayBuilder
import ae.altkamul.papper.R

private const val TAG = "WelcomeMessageRobot"

/**
 * The robot for the tutorial categories.
 */
internal class WelcomeMessageRobot(private val presenter: MainMenuContract.Presenter) :
    MainMenuContract.Robot, RobotLifecycleCallbacks {

    override fun register(activity: MainMenuActivity) {
        QiSDK.register(activity, this)
    }

    override fun unregister(activity: MainMenuActivity) {
        QiSDK.unregister(activity, this)
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
    }

    override fun onRobotFocusLost() {

    }

    override fun onRobotFocusRefused(reason: String) {
        Log.i(TAG, "onRobotFocusRefused: $reason")
    }

    @StringRes
    private fun introSentenceRes(): Int {
        return R.string.welcome_message
    }
}
