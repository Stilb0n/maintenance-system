package org.example

import jakarta.validation.Valid
import org.example.model.CreateRequestDto
import org.example.model.MaintenanceRequest
import org.example.model.UpdateRequestStatusDto
import org.example.service.MaintenanceRequestService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/requests")
class RequestController(
    private val requestService: MaintenanceRequestService
) {

    @GetMapping
    fun getAllRequests(): List<MaintenanceRequest> {
        return requestService.getAllRequests()
    }

    @GetMapping("/{id}")
    fun getRequestById(@PathVariable id: Long): MaintenanceRequest {
        return requestService.getRequestById(id)
    }

    @PatchMapping("/{id}/status")
    fun updateRequestStatus(
        @PathVariable id: Long,
        @Valid @RequestBody updRequest: UpdateRequestStatusDto
    ): MaintenanceRequest {
        return requestService.updateRequestStatus(id, updRequest)
    }

    @PostMapping
    fun createRequest(
        @Valid @RequestBody request: CreateRequestDto
    ): MaintenanceRequest {
        return requestService.createRequest(request)
    }

    @DeleteMapping("/{id}")
    fun deleteRequest(@PathVariable id: Long): ResponseEntity<Void> {
        requestService.deleteRequest(id)
        return ResponseEntity.noContent().build()
    }
}