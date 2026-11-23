package org.example

import org.example.model.CreateRequestDto
import org.example.model.MaintenanceRequest
import org.example.model.UpdateRequestStatusDto
import org.example.service.MaintenanceRequestService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/requests")
// сервис через конструктор внедряется и теперь можно использовать его(сервиса) CRUD методы .
class RequestController(private val requestService: MaintenanceRequestService) {

    @GetMapping
    fun getAllRequests(): List<MaintenanceRequest> {
        return requestService.getAllRequests()
    }
    @GetMapping("/{id}")
    fun getRequestById(@PathVariable id: Long): ResponseEntity<MaintenanceRequest> {
        val request = requestService.getRequestById(id)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(request)
    }
    @PatchMapping("/{id}/status")
    fun updateRequestStatus(
        @PathVariable id: Long,
        @RequestBody updRequest: UpdateRequestStatusDto
    ): ResponseEntity<MaintenanceRequest> {
        val updatedRequest = requestService.updateRequestStatus(id, updRequest)
        return if (updatedRequest != null) {
            ResponseEntity.ok(updatedRequest)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createRequest(@RequestBody request: CreateRequestDto): MaintenanceRequest {
        return requestService.createRequest(request)
    }
    @DeleteMapping("/{id}")
    fun deleteRequest(@PathVariable id: Long): ResponseEntity<Void> {
        val deleted = requestService.deleteRequest(id)

        return if (deleted) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
