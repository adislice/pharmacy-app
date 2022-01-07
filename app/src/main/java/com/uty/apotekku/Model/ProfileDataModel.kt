package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

data class ProfileDataModel (
    @SerializedName("username") val unameUser: String?,
    @SerializedName("nama") val namaUser: String?,
    @SerializedName("email") val emailUser: String?,
    @SerializedName("alamat") val alamatUser: String?,
    @SerializedName("no_telp") val notelpUser: String,
)