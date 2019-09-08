package com.baton.jsontoview.data.entity


import com.google.gson.annotations.SerializedName

data class Element(
    @SerializedName("condition")
    val condition: Condition?,
    @SerializedName("content")
    val content: Content?,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: ElementType,
    @SerializedName("validator")
    val validator: Validator?,
    @SerializedName("value")
    val value: String,
    @SerializedName("view")
    val view: MyView?,
    @SerializedName("semantics")
    val semantics: Semantics?
)

enum class ElementType(val type: String) {
    @SerializedName("field")
    FIELD("field"),
    @SerializedName("dependency")
    DEPENDENCY("dependency")
}