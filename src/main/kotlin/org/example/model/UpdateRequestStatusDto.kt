package org.example.model

import jakarta.validation.constraints.NotBlank

data class UpdateRequestStatusDto(
    @field:NotBlank(message = "Статус заявки не должен быть пустым")
    val status: String
)