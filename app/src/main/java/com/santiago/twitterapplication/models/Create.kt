package com.santiago.twitterapplication.models

class Create {
    var name =  ""
    var job = ""

    constructor(name: String, job: String) {
        this.name = name
        this.job = job
    }

    override fun toString(): String {
        return "Create(name='$name', job='$job')"
    }


}