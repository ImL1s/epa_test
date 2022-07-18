package com.iml1s.epa.main.model

import com.squareup.moshi.Json

data class EpaResponse(
    val sort: String,

    @Json(name = "include_total")
    val includeTotal: Boolean,

    @Json(name = "resource_id")
    val resourceID: String,

    val fields: List<Field>,

    @Json(name = "__extras")
    val extras: Extras,

    @Json(name = "records_format")
    val recordsFormat: String,

    val records: List<Record>,
    val limit: Long,
    val offset: Long,

    @Json(name = "_links")
    val links: Links,

    val distribution: List<Distribution>,
    val total: Long
)

data class Distribution(
    val qcLevel: String
)

data class Extras(
    @Json(name = "api_key")
    val apiKey: String
)

data class Field(
    val info: Info,
    val type: Type,
    val id: String
)

data class Info(
    val notes: String,
    val label: String
)

enum class Type(val value: String) {
    Text("text");

    companion object {
        public fun fromValue(value: String): Type = when (value) {
            "text" -> Text
            else -> throw IllegalArgumentException()
        }
    }
}

data class Links(
    val start: String,
    val next: String
)

data class Record(
    @Json(name = "SiteName")
    val siteName: String,

    @Json(name = "County")
    val county: String,

    @Json(name = "AQI")
    val aqi: String,

    @Json(name = "Pollutant")
    val pollutant: String,

    @Json(name = "Status")
    val status: Status,

    @Json(name = "SO2")
    val so2: String,

    @Json(name = "CO")
    val co: String,

    @Json(name = "CO_8hr")
    val co8Hr: String,

    @Json(name = "O3")
    val o3: String,

    @Json(name = "O3_8hr")
    val o38Hr: String,

    @Json(name = "PM10")
    val pm10: String,

    @Json(name = "PM2.5")
    val pm25: String,

    @Json(name = "NO2")
    val no2: String,

    @Json(name = "NOx")
    val nOx: String,

    @Json(name = "NO")
    val no: String,

    @Json(name = "WIND_SPEED")
    val windSpeed: String,

    @Json(name = "WIND_DIREC")
    val windDirec: String,

    @Json(name = "PublishTime")
    val publishTime: String,

    @Json(name = "PM2.5_AVG")
    val pm25_Avg: String,

    @Json(name = "PM10_AVG")
    val pm10Avg: String,

    @Json(name = "SO2_AVG")
    val so2Avg: String,

    @Json(name = "Longitude")
    val longitude: String,

    @Json(name = "Latitude")
    val latitude: String,

    @Json(name = "SiteId")
    val siteID: String,

    @Json(name = "ImportDate")
    val importDate: String
)

enum class Status(val value: String) {
    GOOD("良好");

    companion object {
        public fun fromValue(value: String): Status = when (value) {
            "良好" -> GOOD
            else -> throw IllegalArgumentException()
        }
    }
}
