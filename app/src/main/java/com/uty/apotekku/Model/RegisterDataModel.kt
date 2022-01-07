package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

class RegisterDataModel (
    @SerializedName("user_id_terdaftar") val user_id_terdaftar: Int,
    @SerializedName("nama") val namaUser: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val emailUser: String,
    @SerializedName("no_telp") val notelpUser: String,
    @SerializedName("password") val passwordUser: String
)