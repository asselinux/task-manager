package ru.asselinux.pomrom.scenes

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager.LayoutParams.*
import android.widget.TextView
import ru.asselinux.pomrom.R
import ru.asselinux.pomrom.firebase.FirestoreClass

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        window.setFlags(
//            FLAG_FULLSCREEN,
//            FLAG_FULLSCREEN
//        )

        var typeRound = Typeface.createFromAsset(assets, "Rounded.ttf")
        typeRound = findViewById<TextView>(R.id.pm_app_name).typeface

        Handler().postDelayed({
            val currentUserID = FirestoreClass().getCurrentUserId()
            if(currentUserID.isNotEmpty()) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
            }

            finish()
        }, 2000)
    }
}