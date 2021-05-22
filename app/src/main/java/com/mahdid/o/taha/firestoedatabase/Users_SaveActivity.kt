package com.mahdid.o.taha.firestoedatabase

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_users__save.*
import kotlin.collections.HashMap

class Users_SaveActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users__save)
        db = Firebase.firestore
        var username = ed_name
        var address = ed_address
        var uid = ed_uid

        btn_dataActivity.setOnClickListener {
            val intent = Intent(this, SplashScreen::class.java)
            startActivity(intent)
            finish()
        }

        btn_save.setOnClickListener {
            if (username.text.isNotEmpty() && address.text.isNotEmpty() && uid.text.isNotEmpty()) {
                var user = HashMap<String, Any>()
                user["username"] = username.text.toString()
                user["address"] = address.text.toString()
                user["uid"] = uid.text.toString()
                user_progressUpload.visibility = ProgressBar.VISIBLE
                hint.visibility = TextView.VISIBLE
                hint.text = "Uploading..."
                db.collection("users_cloud").add(user).addOnSuccessListener {
                    hint.setTextColor(Color.GREEN)
                    hint.text = "User added are successfully!"
                    user_progressUpload.visibility = ProgressBar.GONE
                    hint.visibility = TextView.GONE
                    username.text.clear()
                    address.text.clear()
                    uid.text.clear()
                }
                    .addOnFailureListener {
                        Snackbar.make(
                            user_saveRoot,
                            "User added are Failed! $it",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        user_progressUpload.visibility = ProgressBar.GONE
                    }
            } else {
                hint.visibility = TextView.VISIBLE
                hint.setTextColor(Color.RED)
                hint.text = "Fill Fields!"
            }
        }
    }
}