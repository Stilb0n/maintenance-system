package org.example

import org.example.model.CreateRequestDto
import org.example.model.MaintenanceRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/requests")
class RequestController {

    private val requests = mutableListOf(
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
    private var nextId = 3L

    @GetMapping
    fun getAllRequests(): List<MaintenanceRequest> {
        return requests
    }
    @PostMapping
    fun createRequest(@RequestBody request: CreateRequestDto): MaintenanceRequest {
        val newRequest = MaintenanceRequest(
            id = nextId++,
            equipmentName = request.equipmentName,
            description = request.description,
            status = "Новая"
        )
        requests.add(newRequest)
        return newRequest
    }
}