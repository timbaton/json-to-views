package com.baton.jsontoview.data.entity


import com.google.gson.annotations.SerializedName

data class Semantics(
    @SerializedName("type")
    val type: String
)