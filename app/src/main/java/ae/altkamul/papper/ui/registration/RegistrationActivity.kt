package ae.altkamul.papper.ui.registration

import ae.altkamul.papper.R
import android.os.Bundle
import android.widget.TextView
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy

class RegistrationActivity : RobotActivity(), RegistrationContract.View {
    private lateinit var presenter: RegistrationContract.Presenter
    private lateinit var robot: RegistrationContract.Robot
    private lateinit var router: RegistrationContract.Router
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.OVERLAY)
        val presenter = RegistrationPresenter()
        robot = RegistrationRobot(presenter)
        presenter.bind(this)
        robot.register(this)
        this.presenter = presenter
        textView = findViewById(R.id.registration_welcome_message)

    }

    override fun updateWelcomeMessage(sentence: String) {
        runOnUiThread{
            textView?.text = sentence
        }
    }
}