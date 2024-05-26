package com.example.invoice.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table (name = "product")
class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var description: String? = null
    var brand: String? = null
    var price: BigDecimal? = null
    var stock: Int? = null
}