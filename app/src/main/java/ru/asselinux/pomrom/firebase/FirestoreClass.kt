package ru.asselinux.pomrom.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import ru.asselinux.pomrom.models.Users
import ru.asselinux.pomrom.scenes.SignupActivity
import ru.asselinux.pomrom.utlis.Constants

class FirestoreClass {
    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignupActivity, userInfo: Users) {
        mFirestore.collection(Constants.USERS)
            .document(geCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
                //activity.finish()
            }.addOnFailureListener {
                e ->
                Log.e(activity.javaClass.simpleName, "error'ka")
            }
    }

    fun geCurrentUserId() : String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}