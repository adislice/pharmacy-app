package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

class CekKuantitasResponseModel (
    @SerializedName("status") val status: Boolean,
    @SerializedName("qty") val qty: Int
    )