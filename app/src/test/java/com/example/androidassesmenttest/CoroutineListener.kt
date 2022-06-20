package com.example.androidassesmenttest

import io.kotest.core.listeners.TestListener
import io.kotest.core.test.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineListener(private val testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestListener {
    override suspend fun beforeInvocation(testCase: TestCase, iteration: Int) {
        super.beforeInvocation(testCase, iteration)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    override suspend fun afterInvocation(testCase: TestCase, iteration: Int) {
        super.afterInvocation(testCase, iteration)
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}
