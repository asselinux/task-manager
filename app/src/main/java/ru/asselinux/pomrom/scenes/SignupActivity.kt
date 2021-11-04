package ru.asselinux.pomrom.scenes

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_singup.*
import ru.asselinux.pomrom.R
import ru.asselinux.pomrom.models.Users
import ru.asselinux.pomrom.firebase.FirestoreClass

class SignupActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)
        setUpToolbar()

        btn_sign_up.setOnClickListener {
            registerUser()
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar_sign_up)

        val toolbar = supportActionBar
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true)
            toolbar.setHomeAsUpIndicator(R.drawable.ic_white_arrow)
        }

        toolbar_sign_up.setNavigationOnClickListener { onBackPressed() }
    }

    private fun registerUser() {
        val name: String = et_name.text.toString().trim { it <= ' ' }
        val email: String = et_email.text.toString().trim { it <= ' ' }
        val password: String = et_password.text.toString().trim { it <= ' ' }

        if (validateForm(name, email, password)) {
            showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        val registerEmail = firebaseUser.email!!
                        val user = Users(firebaseUser.uid, name, registerEmail)

                        FirestoreClass().registerUser(this@SignupActivity, user)
                    } else {
                        Toast.makeText(
                            this@SignupActivity,
                            "Не удалось зарегистрироваться",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    fun userRegisteredSuccess() {
        Toast.makeText(
            this@SignupActivity,
            "Вы успешно зарегистрировались",
            Toast.LENGTH_SHORT
        ).show()

        hideProgressDialog()

        FirebaseAuth.getInstance().signOut()
        finish()
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
            }
            else -> {
                true
            }
        }
    }
}