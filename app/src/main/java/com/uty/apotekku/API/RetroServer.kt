package com.uty.apotekku.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroServer {
    companion object {
        private val baseURL: String = "https://apotekku-webservice.000webhostapp.com"
        private var retro: Retrofit? = null

        fun konekRetrofit(): Retrofit? {
            if (retro == null) {
                retro = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retro
        }
    }
}