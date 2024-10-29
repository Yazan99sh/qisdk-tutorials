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
import com.aldebaran.qi.Consumer
import com.aldebaran.qi.sdk.Qi
import com.aldebaran.qi.sdk.`object`.actuation.Animate
import com.aldebaran.qi.sdk.`object`.conversation.Say
import com.aldebaran.qi.sdk.builder.AnimateBuilder
import com.aldebaran.qi.sdk.builder.AnimationBuilder

private const val TAG = "RegistrationRobot"

/**
 * The robot for the tutorial categories.
 */
internal class RegistrationRobot(private val presenter: RegistrationContract.Presenter) :
    RegistrationContract.Robot, RobotLifecycleCallbacks {
    var qiContexts: QiContext? = null
    var saySuccessMessage: Say? = null
    var danceOnMessageSuccess: Animate? = null
    override fun register(activity: RegistrationActivity) {
        QiSDK.register(activity, this)
    }

    override fun unregister(activity: RegistrationActivity) {
        QiSDK.unregister(activity, this)
    }

    override fun saySuccessMessageAndDance() {
        val sayFuture = saySuccessMessage?.async()?.run()
        sayFuture?.andThenConsume(Consumer {
            Thread.sleep(1000)
            val animateFuture = danceOnMessageSuccess?.async()?.run()
            animateFuture?.andThenConsume(
                Qi.onUiThread(Consumer {
                    presenter.goBackToRegistration()
                })
            )
        })
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
        qiContexts = qiContext
        val sentence1 = qiContext.resources.getString(successMessage())
        saySuccessMessage = SayBuilder.with(qiContext)
            .withText(sentence1)
            .build()
        val animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.elephant_a001).build()
        danceOnMessageSuccess = AnimateBuilder.with(qiContext).withAnimation(animation).build()
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

    @StringRes
    private fun successMessage(): Int {
        return R.string.registration_success_message
    }
}
