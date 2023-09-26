package com.example.osilink.models

class House {
    var name: String = ""
    var email: String = ""
    var phoneNumber: String = ""
    var imageUrl:String = ""
    var valuation: String = ""
    var id: String = ""


    constructor(
        name: String,
        email: String,
        phoneNumber: String,
        imageUrl: String,
        valuation: String,
        id: String
    ) {
        this.name = name
        this.email = email
        this.phoneNumber = phoneNumber
        this.imageUrl = imageUrl
        this.valuation = valuation
        this.id = id
    }
    constructor()
}
