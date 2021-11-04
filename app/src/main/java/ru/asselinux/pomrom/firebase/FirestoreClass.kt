package ru.asselinux.pomrom.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import ru.asselinux.pomrom.models.Users
import ru.asselinux.pomrom.scenes.*
import ru.asselinux.pomrom.utlis.Constants

class FirestoreClass {
    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignupActivity, userInfo: Users) {
        mFirestore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }.addOnFailureListener {
                Log.e(activity.javaClass.simpleName, "error")
            }
    }

    fun loadUserData(activity: Activity) {
        mFirestore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val loggedUser = document.toObject(Users::class.java)!!

                when (activity) {
                    is SigninActivity -> {
                        activity.signInSuccess(loggedUser)
                    }
                    is MainActivity -> {
                        activity.updateNavigationUserDetails(loggedUser)
                    }
                    is UserProfile -> {
                        activity.setUserData(loggedUser)
                    }
                }

            }.addOnFailureListener {
                when (activity) {
                    is SigninActivity -> {
                        activity.hideProgressDialog()
                    }
                    is MainActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                    Log.e(activity.javaClass.simpleName, "error 1")
                }
            }

        fun getCurrentUserId(): String {

            var currentUser = FirebaseAuth.getInstance().currentUser
            var currentUserID = ""
            if (currentUser != null) {
                currentUserID = currentUser!!.uid
            }
            return currentUserID
        }
    }