package com.example.invoice.service

import com.example.invoice.entity.Invoice
import com.example.invoice.entity.InvoiceView
import com.example.invoice.reporitory.InvoiceRepository
import com.example.invoice.reporitory.InvoiceViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class InvoiceService {

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var invoiceViewRepository: InvoiceViewRepository

    fun getTotal (value: Double): List<Invoice>{
        return invoiceRepository.findTotal(value)
    }

    fun list(): List<Invoice> {
        return invoiceRepository.findAll()
    }

    fun listView(): List<InvoiceView> {
        return invoiceViewRepository.findAll()
    }


    fun save(invoice: Invoice): Invoice {
        return invoiceRepository.save(invoice)
    }

    fun update(invoice: Invoice): Invoice {
        try {
            invoiceRepository.findById(invoice.id)
                ?: throw Exception("Invoice not found")
            return invoiceRepository.save(invoice)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateCode(invoice: Invoice): Invoice {
        try {
            val response = invoiceRepository.findById(invoice.id)
                ?: throw Exception("Invoice not found")
            response.apply {
                code = invoice.code
            }
            return invoiceRepository.save(invoice)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long) {
        try {
            val invoiceOptional = invoiceRepository.findById(id)
            if (invoiceOptional.isPresent) {
                val invoice = invoiceOptional.get()
                invoiceRepository.delete(invoice)
            } else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "No invoice found with provided ID")
            }
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting invoice", ex)
        }
    }


}