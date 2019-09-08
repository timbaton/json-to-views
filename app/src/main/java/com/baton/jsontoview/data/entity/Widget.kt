package com.baton.jsontoview.data.entity


import com.google.gson.annotations.SerializedName

data class Widget(
    @SerializedName("choices")
    val choices: List<Choice>?,
    @SerializedName("type")
    val type: ViewType
)

enum class ViewType(val type: String) {
    @SerializedName("field")
    FIELD("field"),
    @SerializedName("radio")
    RADIO("radio")
}