package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

data class DaftarAlkesDataModel (
    @SerializedName("id_alkes") val id_alkes: Int,
    @SerializedName("nama_alkes") val nama_alkes: String?,
    @SerializedName("harga") val harga: Int?,
    @SerializedName("diskon") val diskon: Int?,
    @SerializedName("gambar") val gambar: String
)