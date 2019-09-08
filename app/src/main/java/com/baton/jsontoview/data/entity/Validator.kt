package com.baton.jsontoview.data.entity


import com.google.gson.annotations.SerializedName

data class Validator(
    @SerializedName("message")
    val message: String,
    @SerializedName("predicate")
    val predicate: Predicate,
    @SerializedName("type")
    val type: String
)