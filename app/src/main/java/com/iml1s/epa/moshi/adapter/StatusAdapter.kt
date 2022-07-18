package com.iml1s.epa.moshi.adapter

import com.iml1s.epa.main.model.Status
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class StatusAdapter {

    @FromJson
    fun fromJson(value: String): Status {
        return Status.fromValue(value)
    }

    @ToJson
    fun toJson(status: Status): String {
        return status.value
    }
}