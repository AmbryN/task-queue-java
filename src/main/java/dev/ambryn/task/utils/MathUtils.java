package dev.ambryn.task.utils;

public class MathUtils {
    public static Long factorial(int n) {
        if (n == 0 || n == 1) return 1L;
        return n * factorial(n - 1);
    }

    public static Long fib(int n) {
        if (n < 2) return (long) n;
        return fib(n - 2) + fib(n - 1);
    }
}
