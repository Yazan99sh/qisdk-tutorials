/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.registration

import androidx.annotation.StringRes
import android.util.Log

import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.builder.SayBuilder
import ae.altkamul.papper.R

private const val TAG = "RegistrationRobot"

/**
 * The robot for the tutorial categories.
 */
internal class RegistrationRobot(private val presenter: RegistrationContract.Presenter) :
    RegistrationContract.Robot, RobotLifecycleCallbacks {

    override fun register(activity: RegistrationActivity) {
        QiSDK.register(activity, this)
    }

    override fun unregister(activity: RegistrationActivity) {
        QiSDK.unregister(activity, this)
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
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
        return R.string.enter_you_full_name_and_phone_number
    }
}
