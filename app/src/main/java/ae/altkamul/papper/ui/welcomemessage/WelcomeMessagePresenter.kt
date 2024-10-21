/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.welcomemessage

/**
 * The presenter for the tutorial categories.
 */
internal class WelcomeMessagePresenter : WelcomeMessageContract.Presenter {
    private var view: WelcomeMessageContract.View? = null

    override fun bind(view: WelcomeMessageContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override fun updateWelcomeMessage(sentence: String) {
        view?.updateWelcomeMessage(sentence)
    }

}
