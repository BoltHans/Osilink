package com.example.osilink.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.osilink.models.House
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage


class HouseRepository(var navController: NavHostController, var context: Context) {
    var progress: ProgressDialog

    init {
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    // OPEN GALLERY TO PICK IMAGE
    fun saveHouseRentWithImage(name:String, email:String, phoneNumber:String, valuation:String, filePath: Uri){
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("HouseRent/$id")
        progress.show()

        storageReference.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var houseData = House(name,email, phoneNumber, imageUrl, valuation, id)
                    var dbRef = FirebaseDatabase.getInstance()
                        .getReference().child("HouseRent/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun saveHouseSellWithImage(name:String, email:String, phoneNumber:String, valuation:String, filePath: Uri){
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("HouseSell/$id")
        progress.show()

        storageReference.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var houseData = House(name,email, phoneNumber, imageUrl, valuation, id)
                    var dbRef = FirebaseDatabase.getInstance()
                        .getReference().child("HouseSell/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun viewHouseRent(rent: MutableState<House>, rents: SnapshotStateList<House>): SnapshotStateList<House> {
        var ref = FirebaseDatabase.getInstance().getReference().child("HouseRent")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                rents.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(House::class.java)
                    rent.value = value!!
                    rents.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return rents
    }
    fun viewHouseSell(sell: MutableState<House>, sells: SnapshotStateList<House>): SnapshotStateList<House> {
        var ref = FirebaseDatabase.getInstance().getReference().child("HouseSell")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                sells.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(House::class.java)
                    sell.value = value!!
                    sells.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return sells
    }

    fun deleteHouseSell(id: String) {
            var delRef = FirebaseDatabase.getInstance().getReference()
                .child("HouseSell/$id")
            progress.show()
            delRef.removeValue().addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful) {
                    Toast.makeText(context, "House Sold", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    fun deleteHouseRent(id: String) {
            var delRef = FirebaseDatabase.getInstance().getReference().child("HouseRent/$id")
            progress.show()
            delRef.removeValue().addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful) {
                    Toast.makeText(context, "House Rented", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



