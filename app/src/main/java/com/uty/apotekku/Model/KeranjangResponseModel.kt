package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

class KeranjangResponseModel (
    @SerializedName("status") val status: Boolean,
    @SerializedName("result") val result: ArrayList<KeranjangDataModel>
    )