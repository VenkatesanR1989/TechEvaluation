package com.techevaluationtask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.techevaluationtask.model.repository.GetPayload

class GetResponse {
    @SerializedName("payload")
    @Expose
    var payLoad: ArrayList<GetPayload>? = null

}