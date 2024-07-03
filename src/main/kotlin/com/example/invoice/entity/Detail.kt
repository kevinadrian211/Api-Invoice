package com.example.invoice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table



@Entity
@Table(name = "detail")
class Detail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null,

    var quantity: Int? = null,

    var price: Double? = null,

    @Column(name = "subtotal", insertable = false, updatable = false)
    var subtotal: Double? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    var invoice: Invoice? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    var product: Product? = null
)
