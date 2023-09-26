package com.example.osilink.data

import AuthRepository
import android.app.ProgressDialog
import android.widget.Toast
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.osilink.models.Booking
import com.example.osilink.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class BookingRepository(var navController: NavController, var context: Context) {
    var authRepository: AuthRepository
    var progress: ProgressDialog

    init {
        authRepository = AuthRepository(navController, context)
        if (!authRepository.isLoggedIn()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun saveBooking(houseName: String, time: String, date: String) {
        var id = System.currentTimeMillis().toString()
        var bookingData = Booking(houseName, time, date, id)
        var dbRef = FirebaseDatabase.getInstance().getReference()
            .child("Booking/$id")
        progress.show()
        dbRef.setValue(bookingData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun viewBookings(booking: MutableState<Booking>, bookings: SnapshotStateList<Booking>): SnapshotStateList<Booking> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Bookings")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                bookings.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Booking::class.java)
                    booking.value = value!!
                    bookings.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return bookings
    }
    fun deleteBookings(id:String){
        var delRef = FirebaseDatabase.getInstance().getReference().child("Bookings/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Booking deleted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}