package com.baton.jsontoview.data.entity


import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("field")
    val `field`: String,
    @SerializedName("predicate")
    val predicate: Predicate,
    @SerializedName("type")
    val type: String
)