package com.example.osilink.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.osilink.models.Carpenter
import com.example.osilink.models.Plumber
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CarpenterRepository (var navController: NavController, var context: Context) {
        var progress: ProgressDialog

        init {
            progress = ProgressDialog(context)
            progress.setTitle("Loading")
            progress.setMessage("Please wait...")
        }


        fun saveCarpenter(name: String, email: String, phoneNumber: String) {
            var id = System.currentTimeMillis().toString()
            var carpenterData = Plumber(name, email,phoneNumber, id)
            var carpenterRef = FirebaseDatabase.getInstance().getReference()
                .child("Carpenters/$id")
            progress.show()
            carpenterRef.setValue(carpenterData).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful) {
                    Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        fun viewCarpenter(
            carpenter: MutableState<Carpenter>,
            carpenters: SnapshotStateList<Carpenter>
        ): SnapshotStateList<Carpenter> {
            var ref = FirebaseDatabase.getInstance().getReference().child("Carpenters")

            progress.show()
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    progress.dismiss()
                    carpenters.clear()
                    for (snap in snapshot.children) {
                        val value = snap.getValue(Carpenter::class.java)
                        carpenter.value = value!!
                        carpenters.add(value)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })
            return carpenters
        }
    }



