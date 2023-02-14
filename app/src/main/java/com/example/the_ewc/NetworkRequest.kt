package com.example.the_ewc

import API
import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkRequest(private val context: Context) {

    fun getData(callback: (List<Term>) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://christopherlam888.github.io/the-ewc-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(API::class.java)

        val call = api.getData()
        call.enqueue(object : Callback<ArrayList<Term>> {
            override fun onResponse(
                call: Call<ArrayList<Term>>,
                response: Response<ArrayList<Term>>
            ) {
                glossary = response.body()!!
            }

            override fun onFailure(call: Call<ArrayList<Term>>, t: Throwable) {
                // handle the error
                Toast.makeText(context, "No Internet. Using local data.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
