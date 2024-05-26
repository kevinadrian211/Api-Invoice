package com.example.invoice.reporitory

import com.example.invoice.entity.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository : JpaRepository<Invoice, Long> {
    fun findById(id: Long?): Invoice?
}