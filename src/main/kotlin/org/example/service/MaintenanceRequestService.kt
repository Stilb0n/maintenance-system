package org.example.service

import org.example.exception.RequestNotFoundException
import org.example.model.CreateRequestDto
import org.example.model.MaintenanceRequest
import org.example.model.UpdateRequestStatusDto
import org.example.repository.MaintenanceRequestRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MaintenanceRequestService(
    private val requestRepository: MaintenanceRequestRepository
) {

    fun getAllRequests(): List<MaintenanceRequest> {
        return requestRepository.findAll()
    }

    fun getRequestById(id: Long): MaintenanceRequest {
        return requestRepository.findByIdOrNull(id)
            ?: throw RequestNotFoundException("Заявка с ID $id не найдена")
    }

    fun createRequest(request: CreateRequestDto): MaintenanceRequest {
        val newRequest = MaintenanceRequest(
            equipmentName = request.equipmentName,
            description = request.description,
            status = "Новая"
        )

        return requestRepository.save(newRequest)
    }

    fun updateRequestStatus(
        id: Long,
        request: UpdateRequestStatusDto
    ): MaintenanceRequest {
        val existingRequest = getRequestById(id)

        val updatedRequest = existingRequest.copy(status = request.status)

        return requestRepository.save(updatedRequest)
    }

    fun deleteRequest(id: Long) {
        if (!requestRepository.existsById(id)) {
            throw RequestNotFoundException("Заявка с ID $id не найдена")
        }

        requestRepository.deleteById(id)
    }
}