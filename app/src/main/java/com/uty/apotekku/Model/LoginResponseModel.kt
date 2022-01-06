package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

class LoginResponseModel (
    @SerializedName("status") val status: Boolean,
    @SerializedName("result") val result: ArrayList<LoginDataModel>
)