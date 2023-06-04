package dev.ambryn.task.utils

import dev.ambryn.task.utils.MathUtils.factorial
import dev.ambryn.task.utils.MathUtils.fib
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MathUtilsTest {
    @Test
    fun factorial_ZeroShouldReturn_1() {
        Assertions.assertEquals(1, factorial(0))
    }

    @Test
    fun factorial_OneShouldReturn_1() {
        Assertions.assertEquals(1, factorial(1))
    }

    @Test
    fun factorial_TwoShouldReturn_2() {
        Assertions.assertEquals(2, factorial(2))
    }

    @Test
    fun factorial_TenShouldReturn_3628800() {
        Assertions.assertEquals(3628800, factorial(10))
    }

    @Test
    fun fib_ZeroShouldReturn_0() {
        Assertions.assertEquals(0, fib(0))
    }

    @Test
    fun fib_OneShouldReturn_1() {
        Assertions.assertEquals(1, fib(1))
    }

    @Test
    fun fib_TwoShouldReturn_2() {
        Assertions.assertEquals(1, fib(2))
    }

    @Test
    fun fib_TenShouldReturn_55() {
        Assertions.assertEquals(55, fib(10))
    }
}