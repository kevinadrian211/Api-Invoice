package com.example.invoice.service

import com.example.invoice.entity.Detail
import com.example.invoice.reporitory.DetailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DetailService {

    @Autowired
    lateinit var detailRepository: DetailRepository

    fun list(): List<Detail> {
        return detailRepository.findAll()
    }

    fun save(detail: Detail): Detail {
        return detailRepository.save(detail)
    }

    fun update(detail: Detail): Detail {
        try {
            detailRepository.findById(detail.id)
                ?: throw Exception("Detail not found")
            return detailRepository.save(detail)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateQuantity(detail: Detail): Detail {
        try {
            val response = detailRepository.findById(detail.id)
                ?: throw Exception("Detail not found")
            response.apply {
                quantity = detail.quantity
            }
            return detailRepository.save(detail)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long) {
        try {
            val detailOptional = detailRepository.findById(id)
            if (detailOptional.isPresent) {
                val detail = detailOptional.get()
                detailRepository.delete(detail)
            } else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "No detail found with provided ID")
            }
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting detail", ex)
        }
    }
}