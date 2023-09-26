package com.example.osilink.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.osilink.models.Cleaner
import com.example.osilink.models.Plumber
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PlumberRepository (var navController: NavController, var context: Context) {
        var progress: ProgressDialog

        init {
            progress = ProgressDialog(context)
            progress.setTitle("Loading")
            progress.setMessage("Please wait...")
        }


        fun savePlumber(name: String, email: String, phoneNumber: String) {
            var id = System.currentTimeMillis().toString()
            var plumberData = Plumber(name, email,phoneNumber, id)
            var plumberRef = FirebaseDatabase.getInstance().getReference()
                .child("Plumbers/$id")
            progress.show()
            plumberRef.setValue(plumberData).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful) {
                    Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        fun viewPlumber(
            plumber: MutableState<Plumber>,
            plumbers: SnapshotStateList<Plumber>
        ): SnapshotStateList<Plumber> {
            var ref = FirebaseDatabase.getInstance().getReference().child("Plumbers")

            progress.show()
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    progress.dismiss()
                    plumbers.clear()
                    for (snap in snapshot.children) {
                        val value = snap.getValue(Plumber::class.java)
                        plumber.value = value!!
                        plumbers.add(value)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })
            return plumbers
        }
    }


