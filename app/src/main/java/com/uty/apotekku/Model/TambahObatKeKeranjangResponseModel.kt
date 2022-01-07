package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

class TambahObatKeKeranjangResponseModel (
    @SerializedName("status") val status: Boolean,
    @SerializedName("msg") val msg: String
)