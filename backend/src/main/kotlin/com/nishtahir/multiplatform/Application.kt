package com.nishtahir.multiplatform

import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import spark.Spark.get

fun main(args: Array<String>) {
    get("/issues") { request, response ->
        response.type("application/json")

        val list = listOf(Issue(1234, "sample", "Long description here", Severity.MEDIUM, false))
        JSON.stringify(Issue::class.serializer().list, list)
    }
}