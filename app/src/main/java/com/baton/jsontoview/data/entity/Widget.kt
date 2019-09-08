package com.baton.jsontoview.data.entity


import com.google.gson.annotations.SerializedName

data class Widget(
    @SerializedName("choices")
    val choices: List<Choice>?,
    @SerializedName("type")
    val type: String
)