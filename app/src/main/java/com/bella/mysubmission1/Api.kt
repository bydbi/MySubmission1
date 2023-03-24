package com.bella.mysubmission1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") query: String
    ): Call<responseUsers>
}