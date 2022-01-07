package com.uty.apotekku.Model

import com.google.gson.annotations.SerializedName

class TotalBayarResponseModel (@SerializedName("status") val status: Boolean, @SerializedName("result") val result: Int)