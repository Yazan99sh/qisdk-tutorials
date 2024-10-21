/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.main_menu

/**
 * Contract for the Welcome Message.
 */
internal interface MainMenuContract {

    interface View {
    }

    interface Presenter {
        fun bind(view: View)
        fun unbind()
    }

    interface Robot {
        fun register(activity: MainMenuActivity)
        fun unregister(activity: MainMenuActivity)
    }

    interface Router {
    }
}
