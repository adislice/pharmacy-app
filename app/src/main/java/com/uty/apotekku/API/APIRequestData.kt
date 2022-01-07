package com.uty.apotekku.API

import com.uty.apotekku.Model.*
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
    fun alkRetriveData(@Field("aksi") aksi: String): Call<AlkesResponseModel>

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

    @POST("api.php")
    @FormUrlEncoded
    fun cekLoginUser(@Field("aksi") aksi: String, @Field("email") email: String, @Field("password") password: String): Call<LoginResponseModel>

    @POST("api.php")
    @FormUrlEncoded
    fun cekRegisterUser(@Field("aksi") aksi: String, @Field("nama") nama: String, @Field("username") username: String, @Field("email") email: String, @Field("no_telp") notelp: String, @Field("password") password: String): Call<RegisterResponseModel>

    @POST("api.php")
    @FormUrlEncoded
    fun lihat_keranjang(@Field("aksi") aksi: String, @Field("id_user") id_user: Int): Call<KeranjangResponseModel>

    @POST("api.php")
    @FormUrlEncoded
    fun lihat_detail_user(@Field("aksi") aksi: String, @Field("id_user") id_user: Int): Call<ProfileResponseModel>

    @POST("api.php")
    @FormUrlEncoded
    fun tambahObatKeKrj(@Field("aksi") aksi: String, @Field("id_user") id_user: Int, @Field("id_obat") id_obat: Int, @Field("qty") qty: Int): Call<TambahObatKeKeranjangResponseModel>

    @POST("api.php")
    @FormUrlEncoded
    fun cekQtyObat(@Field("aksi") aksi: String, @Field("id_user") id_user: Int, @Field("id_obat") id_obat: Int): Call<CekKuantitasResponseModel>

    @POST("api.php")
    @FormUrlEncoded
    fun cekQtyAlkes(@Field("aksi") aksi: String, @Field("id_user") id_user: Int, @Field("id_alkes") id_obat: Int): Call<CekKuantitasResponseModel>

}