package com.mahdid.o.taha.firestoedatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mahdid.o.taha.firestoedatabase.Adapter.UsersRecyclerAdapter
import com.mahdid.o.taha.firestoedatabase.model.Users
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Firebase.firestore
        btn_userAdd.setOnClickListener {
            val intent = Intent(this, Users_SaveActivity::class.java)
            startActivity(intent)
            finish()
        }
        val list = intent.getParcelableArrayListExtra<Users>("list")
        if (list == null) {
            Toast.makeText(this, "There is no data", Toast.LENGTH_SHORT).show()
        } else {
            var adapter = UsersRecyclerAdapter(this, list as ArrayList<Users>)
            users_recycler_view.layoutManager = LinearLayoutManager(this)
            users_recycler_view.adapter = adapter
        }


    }
}