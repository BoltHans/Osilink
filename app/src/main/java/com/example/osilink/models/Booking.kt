package com.example.osilink.models

class Booking{
    var houseName: String = ""
    var date: String = ""
    var time: String = ""
    var id: String = ""

    constructor(houseName: String, date: String, time:String, id: String) {
        this.houseName = houseName
        this.date = date
        this.time = time
        this.id = id

    }
    constructor()
}