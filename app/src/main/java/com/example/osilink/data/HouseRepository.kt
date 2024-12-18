package com.example.osilink.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.osilink.models.House
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class HouseRepository(var navController: NavHostController, var context: Context) {
    private val progress: ProgressDialog

    init {
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    // Function to retrieve houses for rent
    fun viewHouseRent(): Flow<List<House>> = callbackFlow {
        val ref = FirebaseDatabase.getInstance().getReference("HouseRent")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val houses = mutableListOf<House>()
                for (snap in snapshot.children) {
                    snap.getValue(House::class.java)?.let { houses.add(it) }
                }
                trySend(houses) // Emit the list to the flow
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException()) // Close flow on error
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) } // Cleanup
    }

    // Function to retrieve houses for sale
    fun viewHouseSell(): Flow<List<House>> = callbackFlow {
        val ref = FirebaseDatabase.getInstance().getReference("HouseSell")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val houses = mutableListOf<House>()
                for (snap in snapshot.children) {
                    snap.getValue(House::class.java)?.let { houses.add(it) }
                }
                trySend(houses) // Emit the list to the flow
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException()) // Close flow on error
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    // Function to save rental house with image
    fun saveHouseRentWithImage(
        name: String,
        email: String,
        phoneNumber: String,
        valuation: String,
        imageUri: Uri
    ) {
        progress.show()

        val storageRef = FirebaseStorage.getInstance().reference.child("HouseRentImages/${System.currentTimeMillis()}.jpg")
        storageRef.putFile(imageUri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { imageUrl ->
                    val house = House(
                        name = name,
                        email = email,
                        phoneNumber = phoneNumber,
                        imageUrl = imageUrl.toString(),
                        valuation = valuation,
                        id = FirebaseDatabase.getInstance().getReference().push().key ?: ""
                    )
                    FirebaseDatabase.getInstance()
                        .getReference("HouseRent")
                        .child(house.id)
                        .setValue(house)
                        .addOnSuccessListener {
                            progress.dismiss()
                            Toast.makeText(context, "House added for rent!", Toast.LENGTH_SHORT).show()
                            navController.navigate("ROUTE_RENT")
                        }
                }
            }
            .addOnFailureListener {
                progress.dismiss()
                Toast.makeText(context, "Failed to upload image: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // Function to save sale house with image
    fun saveHouseSellWithImage(
        name: String,
        email: String,
        phoneNumber: String,
        valuation: String,
        imageUri: Uri
    ) {
        progress.show()

        val storageRef = FirebaseStorage.getInstance().reference.child("HouseSellImages/${System.currentTimeMillis()}.jpg")
        storageRef.putFile(imageUri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { imageUrl ->
                    val house = House(
                        name = name,
                        email = email,
                        phoneNumber = phoneNumber,
                        imageUrl = imageUrl.toString(),
                        valuation = valuation,
                        id = FirebaseDatabase.getInstance().getReference().push().key ?: ""
                    )
                    FirebaseDatabase.getInstance()
                        .getReference("HouseSell")
                        .child(house.id)
                        .setValue(house)
                        .addOnSuccessListener {
                            progress.dismiss()
                            Toast.makeText(context, "House added for sale!", Toast.LENGTH_SHORT).show()
                            navController.navigate("ROUTE_BUY")
                        }
                }
            }
            .addOnFailureListener {
                progress.dismiss()
                Toast.makeText(context, "Failed to upload image: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}

