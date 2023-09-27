package com.example.osilink.models

class Booking{
    var houseName: String = ""
    var date: String = ""
    var time: String = ""
    var id: String = ""

    constructor(houseName: String, selectedDate: String, time:String, id: String) {
        this.houseName = houseName
        this.date = selectedDate
        this.time = time
        this.id = id

    }
    constructor()
}