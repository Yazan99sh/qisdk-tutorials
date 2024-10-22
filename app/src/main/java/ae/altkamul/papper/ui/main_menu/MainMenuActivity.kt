package ae.altkamul.papper.ui.main_menu

import ae.altkamul.papper.R
import ae.altkamul.papper.ui.welcomemessage.WelcomeMessageContract
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy

class MainMenuActivity : RobotActivity(), MainMenuContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.OVERLAY)
        setContentView(R.layout.activity_main_menu)
    }
}