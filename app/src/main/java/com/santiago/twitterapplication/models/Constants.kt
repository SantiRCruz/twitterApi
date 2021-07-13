package com.santiago.twitterapplication.models

class Constants {
    companion object{
        //ID
        var LIST_ID=0

        //api
        val API_URL="https://reqres.in/api/"

        //BD
        val DB_NAME = "twitter"
        val DB_VERSION = 1

        val TABLE_NAME = "users"
        val TABLE_COLUMN_1 = "id"
        val TABLE_COLUMN_2 = "email"
        val TABLE_COLUMN_3 = "first_name"
        val TABLE_COLUMN_4 = "last_name"
        val TABLE_COLUMN_5 = "avatar"

        val QUERY_ALL = " SELECT * FROM " + TABLE_NAME

        val TABLE_CREATE = " CREATE TABLE  " + TABLE_NAME + " ( " +
               TABLE_COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
               TABLE_COLUMN_2 + " TEXT NOT NULL , " +
               TABLE_COLUMN_3 + " TEXT NOT NULL , " +
               TABLE_COLUMN_4 + " TEXT NOT NULL , " +
               TABLE_COLUMN_5 + " BLOB ) "
    }
}