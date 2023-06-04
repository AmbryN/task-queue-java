package dev.ambryn.task.utils

import kotlinx.coroutines.isActive
import kotlinx.coroutines.yield
import kotlin.coroutines.coroutineContext

object MathUtils {
    fun factorial(n: Int): Long {
        return if (n == 0 || n == 1) 1L else n * factorial(n - 1)
    }

    suspend fun fib(n: Int): Long {
        if (!coroutineContext.isActive) {
            yield()
        }
        return if (n < 2) n.toLong() else fib(n - 2) + fib(
            n - 1
        )
    }
}
