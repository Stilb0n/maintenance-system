package org.example

import org.example.model.CreateRequestDto
import org.example.model.MaintenanceRequest
import org.example.model.UpdateRequestStatusDto
import org.example.repository.MaintenanceRequestRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/requests")
class RequestController(private val requestRepository: MaintenanceRequestRepository) {




    @GetMapping
    fun getAllRequests(): List<MaintenanceRequest> {
        return requestRepository.findAll()
    }
    @GetMapping("/{id}")
    fun getRequestById(@PathVariable id: Long): ResponseEntity<MaintenanceRequest> {
        val request = requestRepository.findByIdOrNull(id)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(request)
    }
    @PatchMapping("/{id}/status")
    fun updateRequestStatus(
        @PathVariable id: Long,
        @RequestBody request: UpdateRequestStatusDto
    ): ResponseEntity<MaintenanceRequest> {

        val existingRequest = requestRepository.findByIdOrNull(id)
            ?: return ResponseEntity.notFound().build()
        val updatedRequest = existingRequest.copy(status = request.status)
        return ResponseEntity.ok(requestRepository.save(updatedRequest))
    }

    @PostMapping
    fun createRequest(@RequestBody request: CreateRequestDto): MaintenanceRequest {
        val newRequest = MaintenanceRequest(
            equipmentName = request.equipmentName,
            description = request.description,
            status = "Новая"
        )
        return requestRepository.save(newRequest)
    }
    @DeleteMapping("/{id}")
    fun deleteRequest(@PathVariable id: Long): ResponseEntity<Void> {

        if (!requestRepository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }

        requestRepository.deleteById(id)

        return ResponseEntity.noContent().build()
    }
}
