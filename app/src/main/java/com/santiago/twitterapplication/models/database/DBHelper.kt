package com.santiago.twitterapplication.models.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.santiago.twitterapplication.models.Constants

class DBHelper(context: Context?) : SQLiteOpenHelper(context, Constants.DB_NAME, null, Constants.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constants.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}