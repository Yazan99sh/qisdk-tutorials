/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.main_menu

/**
 * The presenter for the tutorial categories.
 */
internal class MainMenuPresenter : MainMenuContract.Presenter {
    private var view: MainMenuContract.View? = null

    override fun bind(view: MainMenuContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override fun updateWelcomeMessage(sentence: String) {
        view?.updateWelcomeMessage(sentence)
    }

}
