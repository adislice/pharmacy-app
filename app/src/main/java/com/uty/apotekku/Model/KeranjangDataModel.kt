package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

class KeranjangDataModel (
    @SerializedName("id_produk") val id_produk: Int,
    @SerializedName("nama_produk") val nama_produk: String,
    @SerializedName("kategori_produk") val kategori_produk: String,
    @SerializedName("harga") val harga: Int,
    @SerializedName("diskon") val diskon: Int,
    @SerializedName("qty") val qty: Int,
    @SerializedName("harga_setelah_diskon") val harga_setelah_diskon: Int,
    @SerializedName("total_termasuk_diskon") val total_termasuk_diskon: Int,
    @SerializedName("gambar") val gambar: String)