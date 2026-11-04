package org.example

import org.example.model.MaintenanceRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/requests")
class RequestController {

    private val requests = listOf(
        MaintenanceRequest(
            id = 1,
            equipmentName = "Кондиционер №1",
            description = "Не охлаждает помещение",
            status = "Новая"
        ),
        MaintenanceRequest(
            id = 2,
            equipmentName = "Сервер №2",
            description = "Повышенная температура",
            status = "В работе"
        )
    )

    @GetMapping
    fun getAllRequests(): List<MaintenanceRequest> {
        return requests
    }
}