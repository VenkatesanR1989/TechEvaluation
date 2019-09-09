package com.techevaluationtask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostApiResponse {

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("success")
    @Expose
    var success: String? = null

    @SerializedName("Payload")
    @Expose
    var payload: Payload? = null
}