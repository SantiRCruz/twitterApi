package com.santiago.twitterapplication.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.santiago.twitterapplication.R
import com.santiago.twitterapplication.interfaces.ApiService
import com.santiago.twitterapplication.models.Constants
import com.santiago.twitterapplication.models.database.DBHelper
import com.santiago.twitterapplication.models.database.DBManager
import com.santiago.twitterapplication.models.users.Data
import com.santiago.twitterapplication.models.users.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashActivity : AppCompatActivity() {

    var dbManager : DBManager ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val dbHelper = DBHelper(applicationContext)
        dbHelper.writableDatabase

        dbManager = DBManager(applicationContext)

        //retrofitUsers()
        startTimer()
    }

    private fun retrofitUsers() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val servicio = retrofit.create(ApiService::class.java)
        servicio.getListUsers(2).enqueue(object : Callback<Users>{
            override fun onResponse(call: Call<Users>?, response: Response<Users>?) {
                for (i in 0..response!!.body().data.size-1){
                    var result = dbManager?.insertar(Data(response.body().data[i].email,response.body().data[i].first_name,response.body().data[i].last_name,response.body().data[i].avatar))
                    if (result!!>0){
                        Toast.makeText(applicationContext, "se inserto correctamente", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext, "no se inserto correctamente", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<Users>?, t: Throwable?) {
                Log.e("Error>>>",t?.message.toString())
            }
        })

    }

    private fun startTimer() {
        object : CountDownTimer(2000,1000){
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}