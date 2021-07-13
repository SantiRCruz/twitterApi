package com.santiago.twitterapplication.models.users

class Users {

     var page = 0
     var per_page = 0
     var total = 0
     var total_pages = 0
     var data = ArrayList<Data>()

     constructor(page: Int, per_page: Int, total: Int, total_pages: Int, data: ArrayList<Data>) {
          this.page = page
          this.per_page = per_page
          this.total = total
          this.total_pages = total_pages
          this.data = data
     }

     override fun toString(): String {
          return "Users(page=$page, per_page=$per_page, total=$total, total_pages=$total_pages, data=$data)"
     }


}