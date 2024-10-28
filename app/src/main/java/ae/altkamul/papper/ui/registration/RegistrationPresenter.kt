package ae.altkamul.papper.ui.registration

import ae.altkamul.papper.database.data_model.User
import ae.altkamul.papper.model.repository.RegistrationLocalRepository
import android.content.Context

/**
 * The presenter for the tutorial categories.
 */
internal class RegistrationPresenter : RegistrationContract.Presenter {
    private var view: RegistrationContract.View? = null
    private var registrationLocalRepository: RegistrationLocalRepository? = null
    override fun init(context: Context) {
        registrationLocalRepository = RegistrationLocalRepository.getInstance(context)
    }

    override fun bind(view: RegistrationContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override fun updateWelcomeMessage(sentence: String) {
        view?.updateWelcomeMessage(sentence)
    }

    override fun goBackToRegistration() {
        view?.goBackToRegistration()
    }

    override fun registerUser(user: User) {
        val success = registrationLocalRepository?.registerUser(user)
        if (success == true) {
            view?.changeToSuccess()
            view?.saySuccessMessage()
            // let the robot dance .
            // go back to form .
        } else {
            // show toast message .
            // let the robot say the failure message .
            // let the robot cry .
        }
}
}
