package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

data class DaftarObatDataModel (
    @SerializedName("id_obat") val id_obat: Int,
    @SerializedName("nama_obat") val nama_obat: String?,
    @SerializedName("harga") val harga: Int?,
    @SerializedName("diskon") val diskon: Int?,
    @SerializedName("gambar") val gambar: String
)