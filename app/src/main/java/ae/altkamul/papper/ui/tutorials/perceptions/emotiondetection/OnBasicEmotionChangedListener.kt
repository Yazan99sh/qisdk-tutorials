/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.tutorials.perceptions.emotiondetection

/**
 * Listener used to notify when the basic emotion changes.
 */
interface OnBasicEmotionChangedListener {
    fun onBasicEmotionChanged(basicEmotion: BasicEmotion)
}
