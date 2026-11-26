package org.example.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    // Обработка отсутствующей заявки
    @ExceptionHandler(RequestNotFoundException::class)
    fun handleNotFound(ex: RequestNotFoundException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to (ex.message ?: "Заявка не найдена")))
    }

    // Обработка ошибок валидации DTO
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, Any>> {
        val errors = ex.bindingResult.fieldErrors.associate {
            it.field to (it.defaultMessage ?: "Некорректное значение")
        }

        return ResponseEntity
            .badRequest()
            .body(
                mapOf(
                    "error" to "Ошибка валидации",
                    "details" to errors
                )
            )
    }
}