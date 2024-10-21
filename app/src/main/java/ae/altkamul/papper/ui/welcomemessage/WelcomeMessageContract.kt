/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.welcomemessage

/**
 * Contract for the Welcome Message.
 */
internal interface WelcomeMessageContract {

    interface View {
        fun updateWelcomeMessage(sentence: String)
    }

    interface Presenter {
        fun bind(view: View)
        fun unbind()
        fun updateWelcomeMessage(sentence: String)
    }

    interface Robot {
        fun register(activity: WelcomeMessageActivity)
        fun unregister(activity: WelcomeMessageActivity)
    }

    interface Router {
    }
}
