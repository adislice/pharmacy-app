package com.uty.apotekku.API

import com.uty.apotekku.Model.ObatResponseModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface APIRequestData {
    @POST("api.php")
    @FormUrlEncoded
    fun ardRetriveData(@Field("aksi") aksi: String): Call<ObatResponseModel>

    @POST("api.php")
    @FormUrlEncoded
    fun getDetailObat(@Field("aksi") aksi: String, @Field("id_obat") id_obat: Int): Call<ObatResponseModel>
}