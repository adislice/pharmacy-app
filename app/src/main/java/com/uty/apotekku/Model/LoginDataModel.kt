package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

class LoginDataModel (
    @SerializedName("id_user") val id_user: Int,
    @SerializedName("nama") val nama_user: String,
    @SerializedName("username") val username: String

)