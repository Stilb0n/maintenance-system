package org.example.model

data class MaintenanceRequest(
    val id: Long,
    val equipmentName: String,
    val description: String,
    val status: String
)
