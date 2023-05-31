package dev.ambryn.task.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MathUtilsTest {
    @Test
    public void factorial_ZeroShouldReturn_1() {
        assertEquals(1, MathUtils.factorial(0));
    }

    @Test
    public void factorial_OneShouldReturn_1() {
        assertEquals(1, MathUtils.factorial(1));
    }

    @Test
    public void factorial_TwoShouldReturn_2() {
        assertEquals(2, MathUtils.factorial(2));
    }

    @Test
    public void factorial_TenShouldReturn_3628800() {
        assertEquals(3628800, MathUtils.factorial(10));
    }

    @Test
    public void fib_ZeroShouldReturn_0() {
        assertEquals(0, MathUtils.fib(0));
    }

    @Test
    public void fib_OneShouldReturn_1() {
        assertEquals(1, MathUtils.fib(1));
    }

    @Test
    public void fib_TwoShouldReturn_2() {
        assertEquals(1, MathUtils.fib(2));
    }

    @Test
    public void fib_TenShouldReturn_55() {
        assertEquals(55, MathUtils.fib(10));
    }
}