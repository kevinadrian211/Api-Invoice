package com.example.invoice.reporitory

import com.example.invoice.entity.Detail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DetailRepository : JpaRepository<Detail, Long> {
    fun findById(id: Long?): Detail?
}