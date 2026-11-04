package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MaintenanceApplication

fun main(args: Array<String>) {
    runApplication<MaintenanceApplication>(*args)
}
