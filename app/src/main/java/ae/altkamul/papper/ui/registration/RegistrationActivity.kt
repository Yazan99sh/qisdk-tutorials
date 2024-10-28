package ae.altkamul.papper.ui.registration

import ae.altkamul.papper.R
import ae.altkamul.papper.database.data_model.User
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy

class RegistrationActivity : RobotActivity(), RegistrationContract.View {
    private lateinit var presenter: RegistrationContract.Presenter
    private lateinit var robot: RegistrationContract.Robot
    private lateinit var router: RegistrationContract.Router
    var textView: TextView? = null
    lateinit var submitButton: Button
    lateinit var registerForm: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.OVERLAY)
        val presenter = RegistrationPresenter()
        robot = RegistrationRobot(presenter)
        presenter.init(this)
        presenter.bind(this)
        robot.register(this)
        this.presenter = presenter
        textView = findViewById(R.id.registration_welcome_message)
        registerForm = findViewById(R.id.register_form)
    }

    fun submitButtonClicked() {
        submitButton = findViewById(R.id.register)
        submitButton.setOnClickListener({
            val nameEditTextView = findViewById<EditText>(R.id.user_name_text_field)
            val phoneNumberEditTextView = findViewById<EditText>(R.id.user_phone_text_field)
            val name = nameEditTextView.text.toString()
            val phoneNumber = phoneNumberEditTextView.text.toString()
            // register
            presenter.registerUser(User(name, phoneNumber))
        })
    }

    override fun updateWelcomeMessage(sentence: String) {
        runOnUiThread {
            textView?.text = sentence
        }
    }

    override fun changeToSuccess() {
        runOnUiThread {
            setContentView(R.layout.registration_success_state)
        }
    }

    override fun saySuccessMessage() {
        robot.saySuccessMessageAndDance()
    }

    override fun goBackToRegistration() {
        setContentView(R.layout.activity_registration)
    }
}