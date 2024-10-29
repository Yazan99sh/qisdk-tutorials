/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.draw

import androidx.annotation.StringRes
import android.util.Log

import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.builder.SayBuilder
import ae.altkamul.papper.R
import com.aldebaran.qi.Consumer
import com.aldebaran.qi.sdk.Qi
import com.aldebaran.qi.sdk.`object`.actuation.Animate
import com.aldebaran.qi.sdk.builder.AnimateBuilder
import com.aldebaran.qi.sdk.builder.AnimationBuilder

private const val TAG = "RegistrationRobot"

/**
 * The robot for the tutorial categories.
 */
internal class RegistrationRobot(private val presenter: DrawContract.Presenter) :
    DrawContract.Robot, RobotLifecycleCallbacks {
    var qiContexts: QiContext? = null
    override fun register(activity: DrawActivity) {
        QiSDK.register(activity, this)
    }

    override fun unregister(activity: DrawActivity) {
        QiSDK.unregister(activity, this)
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
        qiContexts = qiContext
        val sentence = qiContext.resources.getString(introSentenceRes())
        presenter.updateWelcomeMessage(sentence)
        val say = SayBuilder.with(qiContext)
            .withText(sentence)
            .build()
        say.run()
    }

    override fun onRobotFocusLost() {

    }

    override fun onRobotFocusRefused(reason: String) {
        Log.i(TAG, "onRobotFocusRefused: $reason")
    }

    @StringRes
    private fun introSentenceRes(): Int {
        return R.string.select_lucki_winners
    }
}
