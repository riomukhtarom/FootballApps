package com.dev.rio.footballapps.api

import org.junit.Test

import org.mockito.Mockito

class ApiRepositoryTest {

    @Test
    fun request() {
        val apiRepository = Mockito.mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=4328"
        apiRepository.request(url)
        Mockito.verify(apiRepository).request(url)
    }
}