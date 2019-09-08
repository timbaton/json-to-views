package com.baton.jsontoview.data.entity


import com.google.gson.annotations.SerializedName

data class Predicate(
    @SerializedName("pattern")
    val pattern: String,
    @SerializedName("type")
    val type: String
)