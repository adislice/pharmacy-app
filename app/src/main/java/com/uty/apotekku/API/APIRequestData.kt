package com.uty.apotekku.API

import com.uty.apotekku.Model.AlkesResponseModel
import com.uty.apotekku.Model.DaftarAlkesResponseModel
import com.uty.apotekku.Model.DaftarObatResponseModel
import com.uty.apotekku.Model.ObatResponseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface APIRequestData {
    @POST("api.php")
    @FormUrlEncoded
    fun ardRetriveData(@Field("aksi") aksi: String): Call<ObatResponseModel>

    @POST("api.php")
    @FormUrlEncoded
    fun dobatRetriveData(@Field("aksi") aksi: String): Call<DaftarObatResponseModel>

    @POST("api.php")
    @FormUrlEncoded
    fun dalkesRetriveData(@Field("aksi") aksi: String): Call<DaftarAlkesResponseModel>

    @POST("api.php")
    @FormUrlEncoded
    fun getDetailObat(@Field("aksi") aksi: String, @Field("id_obat") id_obat: Int): Call<ObatResponseModel>

    @POST("api.php")
    @FormUrlEncoded
    fun getDetailAlkes(@Field("aksi") aksi: String, @Field("id_alkes") id_alkes: Int): Call<AlkesResponseModel>
}