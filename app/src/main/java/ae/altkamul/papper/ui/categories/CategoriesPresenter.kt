/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.categories

import ae.altkamul.papper.model.data.Tutorial
import ae.altkamul.papper.model.data.TutorialCategory
import ae.altkamul.papper.model.data.TutorialLevel
import ae.altkamul.papper.model.repository.TutorialRepository

/**
 * The presenter for the tutorial categories.
 */
internal class CategoriesPresenter : CategoriesContract.Presenter {
    private var view: CategoriesContract.View? = null
    private val tutorialRepository: TutorialRepository = TutorialRepository()
    private var loadedTutorials = listOf<Tutorial>()
    private var selectedCategory = TutorialCategory.TALK
    private var selectedLevel = TutorialLevel.BASIC

    override fun bind(view: CategoriesContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    override fun loadTutorials(category: TutorialCategory) {
        selectedCategory = category
        updateTutorials()
    }

    override fun loadTutorials(level: TutorialLevel) {
        selectedLevel = level
        updateTutorials()
    }

    override fun goToTutorialForQiChatbotId(tutorialQiChatbotId: String) {
        for (tutorial in loadedTutorials) {
            if (tutorial.qiChatbotId == tutorialQiChatbotId) {
                view?.selectTutorial(tutorial)
                view?.goToTutorial(tutorial)
                break
            }
        }
    }

    override fun goToTutorial(tutorial: Tutorial) {
        view?.goToTutorial(tutorial)
    }

    private fun updateTutorials() {
        loadedTutorials = tutorialRepository.getTutorials(selectedCategory, selectedLevel)
        view?.selectCategory(selectedCategory)
        view?.selectLevel(selectedLevel)
        view?.showTutorials(loadedTutorials)
    }
}
