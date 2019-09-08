package com.baton.jsontoview.data.entity


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("elements")
    val elements: List<Element>
)