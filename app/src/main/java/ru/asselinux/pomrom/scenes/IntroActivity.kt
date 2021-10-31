package ru.asselinux.pomrom.scenes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.activity_singup.*
import ru.asselinux.pomrom.R

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        btn_sign_in_intro.setOnClickListener{
            startActivity(Intent(this@IntroActivity, SigninActivity::class.java))
        }

        btn_sign_up_intro.setOnClickListener{
            startActivity(Intent(this@IntroActivity, SignupActivity::class.java))
        }
    }
}