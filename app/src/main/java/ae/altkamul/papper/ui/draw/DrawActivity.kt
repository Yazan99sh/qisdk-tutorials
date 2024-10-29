package ae.altkamul.papper.ui.draw

import ae.altkamul.papper.R
import ae.altkamul.papper.database.DatabaseHelper
import ae.altkamul.papper.database.dao.UsersDao
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView

class DrawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)
        val gridLayout = findViewById<GridLayout>(R.id.circle_grid)
        val userDao = UsersDao(this)

        for (i in 1..10) {
            if (i > userDao.getUsers().size) {
                continue
            }
            val textView = TextView(this).apply {
                text = i.toString()
                setBackgroundResource(R.drawable.circle_background)
                setTextColor(resources.getColor(android.R.color.black))
                textSize = 18f
                gravity = android.view.Gravity.CENTER
                // Set layout params for circle
                val params = GridLayout.LayoutParams()
                params.width = 75
                params.height = 75
                params.setMargins(8, 8, 8, 8)
                layoutParams = params
            }
            gridLayout.addView(textView)
        }
    }
}