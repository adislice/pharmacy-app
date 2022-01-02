package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

data class DaftarObatResponseModel (
    @SerializedName("status") val status: Boolean,
    @SerializedName("result") val result: ArrayList<DaftarObatDataModel>
)