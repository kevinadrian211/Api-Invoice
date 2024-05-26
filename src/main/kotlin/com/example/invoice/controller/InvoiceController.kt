package com.example.invoice.controller

import com.example.invoice.entity.Invoice
import com.example.invoice.service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/invoice")
class InvoiceController {

    @Autowired
    lateinit var invoiceService: InvoiceService

    @GetMapping
    fun list(): List<Invoice> {
        return invoiceService.list()
    }

    @PostMapping
    fun save(@RequestBody invoice: Invoice): Invoice {
        return invoiceService.save(invoice)
    }

    @PutMapping
    fun update(@RequestBody invoice: Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.update(invoice), HttpStatus.OK)
    }

    @PatchMapping
    fun updateCode(@RequestBody invoice: Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.updateCode(invoice), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        invoiceService.delete(id)
        return ResponseEntity.ok("Invoice deleted successfully")
    }
}