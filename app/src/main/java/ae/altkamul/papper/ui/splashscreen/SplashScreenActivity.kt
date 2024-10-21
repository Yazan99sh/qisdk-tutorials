/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.splashscreen

import android.content.Intent
import android.os.Bundle

import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy
import ae.altkamul.papper.R
import ae.altkamul.papper.ui.categories.CategoriesActivity
import ae.altkamul.papper.ui.idle.IdleActivity
import ae.altkamul.papper.ui.welcomemessage.WelcomeMessageActivity

import java.util.Timer
import java.util.TimerTask

/**
 * The splashscreen activity.
 */
class SplashScreenActivity : RobotActivity() {

    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.OVERLAY)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onResume() {
        super.onResume()

        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                goToIdlePage()
            }
        }, 1500)
    }

    override fun onPause() {
        timer?.cancel()
        super.onPause()
    }

    private fun goToIdlePage() {
        // IdleActivity
        val intent = Intent(this, WelcomeMessageActivity::class.java)
        startActivity(intent)

        finish()
    }
}
