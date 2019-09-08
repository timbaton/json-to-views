package com.baton.jsontoview.data.entity


import com.google.gson.annotations.SerializedName

data class MyView(
    @SerializedName("prompt")
    val prompt: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("widget")
    val widget: Widget
)