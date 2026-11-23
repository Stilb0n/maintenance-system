package org.example.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

// дата класс запросов обслуживания.
@Entity
data class MaintenanceRequest(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val equipmentName: String,

    val description: String,

    val status: String
)