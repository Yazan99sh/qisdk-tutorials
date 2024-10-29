package ae.altkamul.papper.ui.draw

import ae.altkamul.papper.database.data_model.User
import ae.altkamul.papper.model.repository.RegistrationLocalRepository
import android.content.Context

/**
 * The presenter for the tutorial categories.
 */
internal class DrawPresenter : DrawContract.Presenter {
    private var view: DrawContract.View? = null
    private var registrationLocalRepository: RegistrationLocalRepository? = null
    override fun init(context: Context) {
        registrationLocalRepository = RegistrationLocalRepository.getInstance(context)
    }

    override fun bind(view: DrawContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override fun updateWelcomeMessage(sentence: String) {
        view?.updateWelcomeMessage(sentence)
    }
}
