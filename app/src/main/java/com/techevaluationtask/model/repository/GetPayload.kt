package com.techevaluationtask.model.repository

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetPayload {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null
}