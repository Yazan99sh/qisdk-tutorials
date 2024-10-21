/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.welcomemessage
import androidx.annotation.StringRes
import android.util.Log

import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.builder.SayBuilder
import ae.altkamul.papper.R
import android.widget.TextView

private const val TAG = "WelcomeMessageRobot"

/**
 * The robot for the tutorial categories.
 */
internal class WelcomeMessageRobot(private val presenter: WelcomeMessageContract.Presenter) : WelcomeMessageContract.Robot, RobotLifecycleCallbacks {

    override fun register(activity: WelcomeMessageActivity) {
        QiSDK.register(activity, this)
    }

    override fun unregister(activity: WelcomeMessageActivity) {
        QiSDK.unregister(activity, this)
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
        val sentence = qiContext.resources.getString(introSentenceRes())
        val words = sentence.split(" ")
        val sentences = mutableListOf<String>()
        var currentSentence = StringBuilder()
        for (i in words.indices) {
            currentSentence.append(words[i]).append(" ")
            if ((i + 1) % 6 == 0 || i == words.size - 1) {
                sentences.add(currentSentence.toString().trim())
                currentSentence = StringBuilder()
            }
        }
        // Iterate through each sentence, say it and update the UI
        for (sentence in sentences) {
            // Update the UI (Assuming you have a method to update the UI)
            presenter.updateWelcomeMessage(sentence)

            // Create a Say action for the robot to speak the sentence
            val say = SayBuilder.with(qiContext)
                .withText(sentence)
                .build()

            // Run the Say action (this is blocking, so it will wait until the robot finishes speaking)
            say.run()

            // You can add a delay here if you need a pause between sentences
            // Thread.sleep(1000) // Wait for 1 second before saying the next sentence (optional)
        }
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
