package com.santiago.twitterapplication.models.users

class Data {
    var id= 0
    var email= ""
    var first_name= ""
    var last_name= ""
    var avatar= ""

    constructor(id: Int, email: String, first_name: String, last_name: String, avatar: String) {
        this.id = id
        this.email = email
        this.first_name = first_name
        this.last_name = last_name
        this.avatar = avatar
    }

    constructor()
    constructor(email: String, first_name: String, last_name: String, avatar: String) {
        this.email = email
        this.first_name = first_name
        this.last_name = last_name
        this.avatar = avatar
    }

    constructor(id: Int, email: String, first_name: String, last_name: String) {
        this.id = id
        this.email = email
        this.first_name = first_name
        this.last_name = last_name
    }


    override fun toString(): String {
        return "$id $email $first_name $last_name $avatar"
    }

}