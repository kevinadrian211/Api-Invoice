package com.example.invoice.reporitory

import com.example.invoice.entity.Invoice
import com.example.invoice.entity.InvoiceView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface InvoiceViewRepository : JpaRepository<InvoiceView, Long> {
    fun findById(id: Long?): Invoice?
}


