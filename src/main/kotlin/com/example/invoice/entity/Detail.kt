package com.example.invoice.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "detail")
class Detail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null,

    var quantity: Int? = null,

    var price: BigDecimal? = null,

    @Column(name = "subtotal", insertable = false, updatable = false)
    var subtotal: BigDecimal? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product? = null
)
