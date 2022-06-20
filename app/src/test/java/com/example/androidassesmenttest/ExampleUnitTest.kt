package com.example.androidassesmenttest

import com.example.androidassesmenttest.usecases.usecases.GetVenuesFromRemoteUsecase
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val getVenuesFromRemoteUsecase = mockk<GetVenuesFromRemoteUsecase>()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_isnotCorrect() {
        assertNotEquals(4, 3 + 2)
    }
}
