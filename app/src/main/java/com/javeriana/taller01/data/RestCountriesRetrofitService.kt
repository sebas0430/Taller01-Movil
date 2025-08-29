package com.javeriana.taller01.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.javeriana.taller01.model.Country

data class FlagsDto(val png: String?, val svg: String?)

interface RestCountriesService {
    @GET("v3.1/alpha/{code}")
    suspend fun getCountry(
        @Path("code") code: String): Country
}

object RestCountriesFactory {
    fun makeService(): RestCountriesService {
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestCountriesService::class.java)
    }
}
