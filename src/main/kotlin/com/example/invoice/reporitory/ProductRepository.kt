package com.example.invoice.reporitory

import com.example.invoice.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findById(id: Long?): Product?
}