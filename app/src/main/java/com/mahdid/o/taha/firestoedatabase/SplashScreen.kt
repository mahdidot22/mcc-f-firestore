package com.mahdid.o.taha.firestoedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mahdid.o.taha.firestoedatabase.model.Users
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.io.Serializable

class SplashScreen : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        textView.visibility = TextView.VISIBLE
        splash_prog_bar.visibility = ProgressBar.VISIBLE
        db = Firebase.firestore
        var list = arrayListOf<Users>()
        db.collection("users_cloud").get().addOnCompleteListener { querySnapshot ->
            textView.visibility = TextView.GONE
            splash_prog_bar.visibility = ProgressBar.GONE
            if (querySnapshot.isSuccessful) {
                for (document: QueryDocumentSnapshot in querySnapshot.result!!) {
                    var username = document.getString("username")
                    var address = document.getString("address")
                    var uid = document.getString("uid")
                    Log.e("mdot",username+address+uid)
                    list.add(
                            Users(
                                    username!!, address!!, uid!!
                            )
                    )
                }
                val intent = Intent(this, MainActivity::class.java)
                intent.putParcelableArrayListExtra("list", list)
                startActivity(intent)
                finish()
            } else {
                Snackbar.make(mainRoot, "${querySnapshot.exception}", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}