# Implementation of a Task Queue in Java with Spring Boot

Personal project to better understand multithreading, and the task queue concept.

Multiple endpoints are defined to create a task and to monitor queued / processed / finished tasks.

Each task runs a Fibonacci sequence calculation to simulate a long-running computation,
and returns the nth item of the sequence asynchronously.

The tasks implement the State design pattern for the user to query their status.

## Build with

- [Java](https://www.java.com/fr/)
- [Spring Boot](https://spring.io/projects/spring-boot/)