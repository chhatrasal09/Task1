package com.app.chhatrasal.task1.models

data class ServerResponse(
    val worldpopulation: List<WorldPopulation>
)

data class WorldPopulation(
    val rank: Int,
    val country: String,
    val population: String,
    val flag: String
)