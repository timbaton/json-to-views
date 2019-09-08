package com.baton.jsontoview.data.entity


import com.google.gson.annotations.SerializedName

data class Choice(
    @SerializedName("title")
    val title: String,
    @SerializedName("value")
    val value: String
)