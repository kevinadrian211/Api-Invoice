package com.example.invoice.reporitory

import com.example.invoice.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long> {
    fun findById(id: Long?): Client?
}
