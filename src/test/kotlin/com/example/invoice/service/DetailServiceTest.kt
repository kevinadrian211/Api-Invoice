package com.example.invoice.service

import com.example.invoice.entity.Detail
import com.example.invoice.reporitory.DetailRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class DetailServiceTest {
    @InjectMocks
    lateinit var detailService: DetailService
    @Mock
    lateinit var detailRepository: DetailRepository
    val jsonString= File ("./src/test/resources/client/newDetail.json").readText(Charsets.UTF_8)
    val detailMock = Gson().fromJson(jsonString, Detail::class.java)

    @Test
    fun saveWhenQuantityIsPositive() {
        detailMock.quantity = 12
        Mockito.`when`(detailRepository.save(Mockito.any(Detail::class.java))).thenReturn(detailMock)
        val response = detailService.validateQuantity(detailMock.quantity!!)
        Assertions.assertEquals(true,response)
    }
}