package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

data class ObatDataModel (
    @SerializedName("id_obat") val id_obat: Int,
    @SerializedName("nama_obat") val nama_obat: String?,
    @SerializedName("harga") val harga: Int?,
    @SerializedName("jenis_obat") val jenis_obat: String?,
    @SerializedName("diskon") val diskon: Int?,
    @SerializedName("gambar") val gambar: String,
    @SerializedName("deskripsi") val deskripsi: String?
)