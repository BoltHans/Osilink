package com.example.osilink.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.osilink.models.Cleaner
import com.example.osilink.models.Electrician
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CleanerRepository(var navController: NavController, var context: Context) {
    var progress: ProgressDialog

    init {
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun saveCleaner(name: String, email: String, phoneNumber: String) {
        var id = System.currentTimeMillis().toString()
        var cleanerData = Cleaner(name, email,phoneNumber, id)
        var cleanerRef = FirebaseDatabase.getInstance().getReference()
            .child("Cleaners/$id")
        progress.show()
        cleanerRef.setValue(cleanerData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun viewCleaner(
        cleaner: MutableState<Cleaner>,
        cleaners: SnapshotStateList<Cleaner>
    ): SnapshotStateList<Cleaner> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Cleaners")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                cleaners.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Cleaner::class.java)
                    cleaner.value = value!!
                    cleaners.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return cleaners
    }
}

