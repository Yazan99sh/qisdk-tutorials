package ae.altkamul.papper.ui.welcomemessage

import ae.altkamul.papper.R
import ae.altkamul.papper.model.data.Tutorial
import android.os.Bundle
import android.widget.TextView
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy

class WelcomeMessageActivity : RobotActivity(), WelcomeMessageContract.View {
    private lateinit var presenter: WelcomeMessageContract.Presenter
    private lateinit var robot: WelcomeMessageContract.Robot
    private lateinit var router: WelcomeMessageContract.Router
    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.OVERLAY)
        setContentView(R.layout.activity_welcome_mssage)
        val presenter = WelcomeMessagePresenter()
        robot = WelcomeMessageRobot(presenter)
        presenter.bind(this)
        robot.register(this)
        this.presenter = presenter
        textView = findViewById(R.id.welcome_message_text_view)
    }

    override fun onDestroy() {
        robot.unregister(this)
        presenter.unbind()
        super.onDestroy()
    }

    override fun updateWelcomeMessage(sentence: String) {
        runOnUiThread {
            textView!!.text = sentence
        }
    }

}