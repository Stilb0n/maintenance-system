package org.example.repository

import org.example.model.MaintenanceRequest
import org.springframework.data.jpa.repository.JpaRepository

interface MaintenanceRequestRepository :
    JpaRepository<MaintenanceRequest, Long>
// спринг генерирует сам реализацию этого интерфейса. достаточно нашему унаследоваться от JpaRepository
/*
* Дженерики <MaintenanceRequest, Long>. подсказываем фреймворку,
* с какой сущностью работать (MaintenanceRequest)
* и какой тип данных у её первичного ключа (Long).
*/