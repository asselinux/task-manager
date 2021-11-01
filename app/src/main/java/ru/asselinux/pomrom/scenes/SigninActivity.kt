package ru.asselinux.pomrom.scenes

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*
import ru.asselinux.pomrom.R

class SigninActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        setUpToolbar()

        auth = FirebaseAuth.getInstance()

        btn_sign_in.setOnClickListener {
            singinRegisteredUser()
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar_sign_in)

        val toolbar = supportActionBar
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true)
            toolbar.setHomeAsUpIndicator(R.drawable.ic_black_arrow)
        }

        toolbar_sign_in.setNavigationOnClickListener { onBackPressed() }
    }

    private fun singinRegisteredUser() {
        val email: String = et_email.text.toString().trim { it <= ' ' }
        val password: String = et_password.text.toString().trim { it <= ' ' }

        if (validateForm(email, password)) {
            showProgressDialog(resources.getString(R.string.please_wait))
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    hideProgressDialog()
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success")
                        val user = auth.currentUser
                        startActivity(Intent(this@SigninActivity, MainActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        }

    }

    private fun validateForm(email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Пожалуйста введите почту")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Пожалуйста введите пароль")
                false
            }
            else -> {
                true
            }
        }
    }
}
