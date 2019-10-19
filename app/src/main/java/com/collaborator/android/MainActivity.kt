package com.collaborator.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    // Access a Cloud Firestore instance from your Activity
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = db.collection("issues")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Toast.makeText(this.applicationContext, convertToIssueModel(document).toString(), Toast.LENGTH_LONG).show()
                    Log.d("Main", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Main", "Error getting documents.", exception)
            }
    }
}
