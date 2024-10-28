
package ae.altkamul.papper.ui.registration

import ae.altkamul.papper.database.data_model.User
import android.content.Context

/**
 * Contract for the Welcome Message.
 */
internal interface RegistrationContract {

    interface View {
        fun updateWelcomeMessage(sentence: String)
        fun changeToSuccess()
        fun saySuccessMessage()
        fun goBackToRegistration()
    }

    interface Presenter {
        fun init(context: Context)
        fun bind(view: View)
        fun unbind()
        fun registerUser(user: User)
        fun updateWelcomeMessage(sentence: String)
        fun goBackToRegistration()
    }

    interface Robot {
        fun register(activity: RegistrationActivity)
        fun unregister(activity: RegistrationActivity)
        fun saySuccessMessageAndDance()
    }

    interface Router {
    }
}
