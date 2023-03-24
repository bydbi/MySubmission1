package com.bella.mysubmission1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class mainViewModel : ViewModel() {

    val userList = MutableLiveData<ArrayList<user>>()

    fun setUserSearch(query: String){
        clientRetrofit.apiInstance
            .getUser(query)
            .enqueue(object : Callback<responseUsers>{
                override fun onResponse(
                    call: Call<responseUsers>,
                    response: Response<responseUsers>
                ) {
                    if (response.isSuccessful){
                        userList.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<responseUsers>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getUserSearch(): LiveData<ArrayList<user>>{
        return userList
    }
}