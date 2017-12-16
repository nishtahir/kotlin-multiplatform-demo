package com.nishtahir.multiplatform

import kotlinx.serialization.Serializable

@Serializable
data class Issue(val id: Long,
                 val title: String,
                 val description: String, 
                 val severity: Severity,
                 val completed: Boolean)

enum class Severity {
    LOW,
    MEDIUM,
    HIGH
}