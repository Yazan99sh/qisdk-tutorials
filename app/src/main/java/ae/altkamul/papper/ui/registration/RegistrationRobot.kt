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
import com.aldebaran.qi.sdk.builder.AnimateBuilder
import com.aldebaran.qi.sdk.builder.AnimationBuilder

private const val TAG = "RegistrationRobot"

/**
 * The robot for the tutorial categories.
 */
internal class RegistrationRobot(private val presenter: RegistrationContract.Presenter) :
    RegistrationContract.Robot, RobotLifecycleCallbacks {
    var qiContexts: QiContext? = null
    override fun register(activity: RegistrationActivity) {
        QiSDK.register(activity, this)
    }

    override fun unregister(activity: RegistrationActivity) {
        QiSDK.unregister(activity, this)
    }

    override fun saySuccessMessageAndDance() {
        val sentence = qiContexts!!.resources.getString(successMessage())
        val say = SayBuilder.with(qiContexts)
            .withText(sentence)
            .build()
        say.async().run()
        val animation = AnimationBuilder.with(qiContexts) // Create the builder with the context.
            .withResources(R.raw.raise_both_hands_b001) // Set the animation resource.
            .build()
        val animate: Animate = AnimateBuilder.with(qiContexts)
            .withAnimation(animation)
            .build()
        val animateFuture = animate.async().run()
        animateFuture.andThenConsume(
            Qi.onUiThread(Consumer {
                presenter.goBackToRegistration()
            })
        )
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
        return R.string.enter_you_full_name_and_phone_number
    }

    @StringRes
    private fun successMessage(): Int {
        return R.string.registration_success_message
    }
}
