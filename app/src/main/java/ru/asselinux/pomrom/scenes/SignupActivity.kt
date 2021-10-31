package ru.asselinux.pomrom.scenes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_singup.*
import ru.asselinux.pomrom.R

class SignupActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)
        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar_sign_up)

        val toolbar = supportActionBar
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true)
            toolbar.setHomeAsUpIndicator(R.drawable.ic_black_arrow)
        }

        toolbar_sign_up.setNavigationOnClickListener { onBackPressed() }
    }

    private fun registerUser() {
        val name: String = et_name.text.toString().trim()
    }

    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Пожалуйста введите имя")
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Пожалуйста введите почту")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Пожалуйста введите пароль")
                false
            } else -> {
                true
            }
        }
    }
}