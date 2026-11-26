package org.example.model

import jakarta.validation.constraints.NotBlank

data class CreateRequestDto(
    @field:NotBlank(message = "Название оборудования не должно быть пустым")
    val equipmentName: String,

    @field:NotBlank(message = "Описание заявки не должно быть пустым")
    val description: String
)