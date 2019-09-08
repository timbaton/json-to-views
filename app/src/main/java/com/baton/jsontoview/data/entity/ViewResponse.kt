package com.baton.jsontoview.data.entity


import com.google.gson.annotations.SerializedName

data class ViewResponse(
    @SerializedName("content")
    val content: Content
)