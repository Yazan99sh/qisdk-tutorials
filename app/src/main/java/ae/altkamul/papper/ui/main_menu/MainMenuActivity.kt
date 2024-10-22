package ae.altkamul.papper.ui.main_menu

import ae.altkamul.papper.R
import ae.altkamul.papper.ui.welcomemessage.WelcomeMessageContract
import ae.altkamul.papper.ui.welcomemessage.WelcomeMessagePresenter
import ae.altkamul.papper.ui.welcomemessage.WelcomeMessageRobot
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy

class MainMenuActivity : RobotActivity(), MainMenuContract.View {
    var textView: TextView? = null
    private lateinit var presenter: MainMenuContract.Presenter
    private lateinit var robot: MainMenuContract.Robot
    private lateinit var router: MainMenuContract.Router
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.OVERLAY)
        setContentView(R.layout.activity_main_menu)
        val presenter = MainMenuPresenter()
        robot = MainMenuRobot(presenter)
        presenter.bind(this)
        robot.register(this)
        this.presenter = presenter
        textView = findViewById(R.id.main_menu_welcome_message)
    }

    override fun updateWelcomeMessage(sentence: String) {
        runOnUiThread {
            textView!!.text = sentence
        }
    }
}