package dev.ambryn.task.utils

object MathUtils {
    fun factorial(n: Int): Long {
        return if (n == 0 || n == 1) 1L else n * factorial(n - 1)
    }

    suspend fun fib(n: Int): Long {
        return if (n < 2) n.toLong() else fib(n - 2) + fib(
            n - 1
        )
    }
}
