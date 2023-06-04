package dev.ambryn.task

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
open class TaskApplication : SpringBootServletInitializer() {
    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(TaskApplication::class.java)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit {
            SpringApplication.run(TaskApplication::class.java, *args)
        }
    }
}
