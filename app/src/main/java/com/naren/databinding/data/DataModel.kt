package com.naren.databinding.data

import com.google.gson.annotations.SerializedName

class DataModel(

        @SerializedName("name")
        var name: String,

        @SerializedName("life_span")
        var lifeSpan: String,

        @SerializedName("origin")
        var origin: String,

        @SerializedName("url")
        var imageUrl: String,

        @SerializedName("id")
        var dog_id: Int,

        @SerializedName("breed_group")
        var group: String,

        @SerializedName("breed_for")
        var purpose: String
)