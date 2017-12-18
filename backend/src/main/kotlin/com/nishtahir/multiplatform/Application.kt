package com.nishtahir.multiplatform

import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import spark.Spark.get

fun main(args: Array<String>) {
    val issues = mutableListOf<Issue>()

    get("/issues") { request, response ->
        response.type("application/json")
        JSON.stringify(Issue::class.serializer().list, issues)
    }

    get("/create") { request, response ->
        with(request) {
            val issue = Issue(0, queryParams("Title"),
                    queryParams("Description"),
                    Severity.valueOf(queryParams("Severity").toUpperCase()),
                    false)
            issues.add(issue)
        }
    }
}