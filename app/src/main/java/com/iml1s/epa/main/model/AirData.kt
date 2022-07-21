package com.iml1s.epa.main.model

data class AirData(
    val siteId: String,
    val siteName: String,
    val city: String,
    val quality: String,
    val status: String
) {
    val isStatusGood = Status.fromValue(status) == Status.Good
}


