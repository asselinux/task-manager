package ru.asselinux.pomrom.scenes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_singup.toolbar_sign_up
import ru.asselinux.pomrom.R

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar_sign_in)

        val toolbar = supportActionBar
        if(toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true)
            toolbar.setHomeAsUpIndicator(R.drawable.ic_black_arrow)
        }

        toolbar_sign_in.setNavigationOnClickListener { onBackPressed() }
    }
}