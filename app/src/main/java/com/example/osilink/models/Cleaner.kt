package com.example.osilink.models

class Cleaner {
        var name:String = ""
        var email:String = ""
        var phoneNumber:String = ""
        var userId:String = ""

        constructor(name: String, email: String, phoneNumber: String, userId: String) {
            this.name = name
            this.email = email
            this.phoneNumber = phoneNumber
            this.userId = userId
        }
        constructor()
    }
