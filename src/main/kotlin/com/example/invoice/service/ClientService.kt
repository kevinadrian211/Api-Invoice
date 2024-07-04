package com.example.invoice.service

import com.example.invoice.entity.Client
import com.example.invoice.reporitory.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ClientService {

    @Autowired
    lateinit var clientRepository: ClientRepository

    fun list(): List<Client> {
        return clientRepository.findAll()
    }

    fun save(client: Client): Client {
        return clientRepository.save(client)
    }

    fun update(client: Client): Client {
        try {
            clientRepository.findById(client.id)
                ?: throw Exception("Client not found")
            return clientRepository.save(client)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(client: Client): Client {
        try {
            val response = clientRepository.findById(client.id)
                ?: throw Exception("Client not found")
            response.apply {
                fullname = client.fullname
            }
            return clientRepository.save(client)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long) {
        try {
            val clientOptional = clientRepository.findById(id)
            if (clientOptional.isPresent) {
                val client = clientOptional.get()
                clientRepository.delete(client)
            } else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "No client found with provided ID")
            }
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting client", ex)
        }
    }

    fun validateNui(nui: String?): Boolean?{
        if (nui == null) {
            return false
        }
        val regex = Regex("^\\d{10}$")
        return regex.matches(nui)
    }

}