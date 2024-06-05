package com.example.invoice.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.Date

@Entity
@Table(name = "invoice_view")
class InvoiceView(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null,
    var code: String? = null,

    @Column(name = "created_at")
    var createdAt: Date? = null,
    var total: Double? = null,

    var fullname: String? = null
)