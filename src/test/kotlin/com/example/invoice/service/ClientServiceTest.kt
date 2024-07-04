package com.example.invoice.service

import com.example.invoice.entity.Client
import com.example.invoice.reporitory.ClientRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
@SpringBootTest
class ClientServiceTest {
    @InjectMocks
    lateinit var clientService: ClientService

    @Mock
    lateinit var clientRepository: ClientRepository
    //Lee el archivo desde l ruta
    val jsonString = File("./src/test/resources/client/newClient.json").readText(Charsets.UTF_8)
    // convierte en objeto de tipo Client
    val clientMock = Gson().fromJson(jsonString, Client::class.java)

    @Test
    fun saveWhenClientIsCorrect() {
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.save(clientMock)
        Assertions.assertEquals(response.id, clientMock.id)

    }
    @Test
    fun saveWhenNuiClientIsCorrect() {
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.validateNui(clientMock.nui)
        Assertions.assertEquals(true,response)
    }
    @Test
    fun saveWhenNuiClientIsIncorrect() {
        clientMock.nui= "1563244"
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.validateNui(clientMock.nui)
        Assertions.assertEquals(false,response)
    }
    @Test
    fun saveWhenNuiClientContainsLetters() {
        clientMock.nui= "15632448a"
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.validateNui(clientMock.nui)
        Assertions.assertEquals(false,response)
    }
    @Test
    fun saveWhenNuiClientContainsNull() {
        clientMock.nui= "null"
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.validateNui(clientMock.nui)
        Assertions.assertEquals(false,response)
    }
}