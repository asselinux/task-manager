package ru.asselinux.pomrom.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import ru.asselinux.pomrom.models.Users
import ru.asselinux.pomrom.scenes.SigninActivity
import ru.asselinux.pomrom.scenes.SignupActivity
import ru.asselinux.pomrom.utlis.Constants

class FirestoreClass {
    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignupActivity, userInfo: Users) {
        mFirestore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
                //activity.finish()
            }.addOnFailureListener {
                Log.e(activity.javaClass.simpleName, "error")
            }
    }

    fun signInUser(activity: SigninActivity) {
        mFirestore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val logInUser = document.toObject(Users::class.java)
                if(logInUser != null)
                    activity.signInSuccess(logInUser)
                //activity.finish()
            }.addOnFailureListener {
                Log.e(activity.javaClass.simpleName, "error 1")
            }
    }

    fun getCurrentUserId() : String {

        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if(currentUser != null) {
            currentUserID = currentUser!!.uid
        }
        return  currentUserID
    }
}