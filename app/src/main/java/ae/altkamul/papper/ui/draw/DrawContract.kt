
package ae.altkamul.papper.ui.draw

import ae.altkamul.papper.database.data_model.User
import android.content.Context

/**
 * Contract for the Welcome Message.
 */
internal interface DrawContract {

    interface View {
        fun updateWelcomeMessage(sentence: String)
    }

    interface Presenter {
        fun init(context: Context)
        fun bind(view: View)
        fun unbind()
        fun updateWelcomeMessage(sentence: String)
    }

    interface Robot {
        fun register(activity: DrawActivity)
        fun unregister(activity: DrawActivity)
    }

    interface Router {
    }
}
