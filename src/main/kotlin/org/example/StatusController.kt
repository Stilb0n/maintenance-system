package org.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatusController {

    @GetMapping("/api/status")
    fun getStatus(): Map<String, String> {
        return mapOf(
            "status" to "OK",
            "message" to "Сервер системы технического обслуживания работает"
        )
    }
}
