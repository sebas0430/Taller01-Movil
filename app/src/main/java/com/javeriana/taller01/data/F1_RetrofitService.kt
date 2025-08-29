package com.javeriana.taller01.data

import com.javeriana.taller01.model.remoteResultPiloto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//https://api.openf1.org/v1/drivers?session_key=9684

interface retrofitService {

    @GET("drivers")
    suspend fun listPilotos(
        @Query("session_key") session_key: String
    ): remoteResultPiloto
}

object RetrofitServiceFactory{
    fun makeRetrofitService(): retrofitService {
        return Retrofit.Builder()
            .baseUrl("https://api.openf1.org/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(retrofitService::class.java)

    }
}
