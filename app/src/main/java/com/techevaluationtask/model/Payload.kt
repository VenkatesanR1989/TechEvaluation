package com.techevaluationtask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Payload {
    @SerializedName("referenceNo")
    @Expose
    var referenceNo: String? = null
}