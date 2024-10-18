/*
 * Copyright (C) 2018 Softbank Robotics Europe
 * See COPYING for the license
 */

package ae.altkamul.papper.ui.categories

import ae.altkamul.papper.model.data.Tutorial

/**
 * The listener used to handle a click on a tutorial.
 */
internal interface OnTutorialClickedListener {
    /**
     * Called when a tutorial is clicked.
     * @param tutorial the selected tutorial
     */
    fun onTutorialClicked(tutorial: Tutorial)
}
