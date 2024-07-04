package com.example.invoice.service

import com.example.invoice.entity.Product
import com.example.invoice.reporitory.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    fun list(): List<Product> {
        return productRepository.findAll()
    }

    fun save(product: Product): Product {
        return productRepository.save(product)
    }

    fun update(product: Product): Product {
        try {
            productRepository.findById(product.id)
                ?: throw Exception("Product not found")
            return productRepository.save(product)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateDescription(product: Product): Product {
        try {
            val response = productRepository.findById(product.id)
                ?: throw Exception("Product not found")
            response.apply {
                description = product.description
            }
            return productRepository.save(product)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long) {
        try {
            val productOptional = productRepository.findById(id)
            if (productOptional.isPresent) {
                val product = productOptional.get()
                productRepository.delete(product)
            } else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "No product found with provided ID")
            }
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting product", ex)
        }
    }

    fun validateStock(stock: Int?): Boolean? {
        if (stock == null) {
            return null
        }
        return try {
            val number = stock.toInt()
            number > 0
        } catch (e: NumberFormatException) {
            false
        }
    }
}