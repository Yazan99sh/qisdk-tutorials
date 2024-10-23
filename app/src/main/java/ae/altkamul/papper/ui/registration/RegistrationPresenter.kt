/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.registration

/**
 * The presenter for the tutorial categories.
 */
internal class RegistrationPresenter : RegistrationContract.Presenter {
    private var view: RegistrationContract.View? = null

    override fun bind(view: RegistrationContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override fun updateWelcomeMessage(sentence: String) {
        view?.updateWelcomeMessage(sentence)
    }

}
