package ae.altkamul.papper.ui.draw

import ae.altkamul.papper.R
import ae.altkamul.papper.database.DatabaseHelper
import ae.altkamul.papper.database.dao.UsersDao
import ae.altkamul.papper.database.data_model.User
import ae.altkamul.papper.model.repository.RegistrationLocalRepository
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.layout_robot_output_view.view.*

class DrawActivity : AppCompatActivity() {
    var selectedTextView: TextView? = null
    var selectedRadioButton: RadioButton? = null
    lateinit var gridLayout: GridLayout
    val registrationLocalRepository: RegistrationLocalRepository =
        RegistrationLocalRepository.getInstance(this)
    var isOnline: Boolean? = null
    var selectedWinnersCount: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)
        gridLayout = findViewById<GridLayout>(R.id.circle_grid)
        initGridView(this)
        val radioGroup: RadioGroup = findViewById(R.id.draw_type_radio_button)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = findViewById(checkedId)
            selectedRadioButton?.setBackgroundResource(R.drawable.radio_background)
            selectedRadioButton?.setPadding(0, 0, 0, 0)
            when (checkedId) {
                R.id.radio_online -> {
                    isOnline = true
                    radioButton.setBackgroundResource(R.drawable.selected_radio_button)
                    radioButton.setPadding(0, 0, 2, 2)
                    selectedRadioButton = radioButton
                }
                R.id.radio_offline -> {
                    isOnline = false
                }
            }
        }
        // select winners button click
        val selectWinnersButton: TextView = findViewById(R.id.select_winners)
        selectWinnersButton.setOnClickListener {
            if (selectedWinnersCount != null && isOnline != null) {
                val users = getUsers()
                if (users.isNotEmpty()) {
                    val winners = users.shuffled().take(selectedWinnersCount!!.toInt())
                    for (winner in winners) {
                        println(winner.name)
                    }
                }
            } else {
                Toast.makeText(this, "Please select number of winners", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun initGridView(context: Context) {
        gridLayout.removeAllViews()
        val userDao = UsersDao(this)
        for (i in 1..10) {
            val textView = TextView(this).apply {
                text = i.toString()
                setBackgroundResource(R.drawable.circle_background)
                setTextColor(resources.getColor(android.R.color.black))
                textSize = 18f
                setTypeface(null, android.graphics.Typeface.BOLD)
                gravity = android.view.Gravity.CENTER
                // Set layout params for circle
                val params = GridLayout.LayoutParams()
                params.width = 75
                params.height = 75
                params.setMargins(8, 8, 8, 8)
                layoutParams = params
                setOnClickListener {
                    selectedWinnersCount = text.toString()
                    selectedTextView?.setBackgroundResource(R.drawable.circle_background)
                    selectedTextView?.setPadding(0, 0, 0, 0)
                    selectedTextView = this
                    selectedTextView?.setBackgroundResource(R.drawable.circle_selected_backgroung)
                    selectedTextView?.setPadding(0, 0, 4, 4)
                }
            }
            gridLayout.addView(textView)
        }
    }

    fun getUsers(): List<User> {
        if (isOnline == true) {
            // Call API to get users
        }
        return registrationLocalRepository.getUsers()
    }
}