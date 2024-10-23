/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.registration

/**
 * Contract for the Welcome Message.
 */
internal interface RegistrationContract {

    interface View {
        fun updateWelcomeMessage(sentence: String)
    }

    interface Presenter {
        fun bind(view: View)
        fun unbind()
        fun updateWelcomeMessage(sentence: String)
    }

    interface Robot {
        fun register(activity: RegistrationActivity)
        fun unregister(activity: RegistrationActivity)
    }

    interface Router {
    }
}
