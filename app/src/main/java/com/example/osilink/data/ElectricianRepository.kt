package com.example.osilink.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.osilink.models.Electrician
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ElectricianRepository(var navController: NavController, var context: Context) {
    var progress: ProgressDialog

    init {
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun saveElectrician(Name: String, email: String, phoneNumber: String) {
        var id = System.currentTimeMillis().toString()
        var electricianData = Electrician(Name, email, phoneNumber, id)
        var electricianRef = FirebaseDatabase.getInstance().getReference()
            .child("Electricians/$id")
        progress.show()
        electricianRef.setValue(electricianData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun viewElectrician(
        electrician: MutableState<Electrician>,
        electricians: SnapshotStateList<Electrician>
    ): SnapshotStateList<Electrician> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Electricians")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                electricians.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Electrician::class.java)
                    electrician.value = value!!
                    electricians.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return electricians
    }
}

