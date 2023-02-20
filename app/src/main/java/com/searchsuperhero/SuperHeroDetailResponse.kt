package com.searchsuperhero

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("image") val image: ImageResponse,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("appearance") val appearance: Appearance,
)

data class PowerStatsResponse(
    @SerializedName("intelligence") var intelligence: String,
    @SerializedName("strength") var strength: String,
    @SerializedName("speed") var speed: String,
    @SerializedName("durability") var durability: String,
    @SerializedName("power") var power: String,
    @SerializedName("combat") var combat: String,
)

data class ImageResponse(@SerializedName("url") val url: String)

data class Biography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("first-appearance") val firstAppearance: String,
)

data class Appearance(
    @SerializedName("height") val height: Array<String>,
    @SerializedName("weight") val weight: Array<String>,
)
