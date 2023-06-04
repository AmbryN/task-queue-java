package dev.ambryn.task.controllers

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

/**
 * The task controller should allow :
 * - to create a new async task and return its handler
 * - to follow the progress of the task via its handler
 * - to delete a task via its handler
 * - to get the result one finished
 */
@SpringBootTest
internal class TaskControllerTest {
    var mvc: MockMvc? = null

    @BeforeEach
    fun setup(context: WebApplicationContext?) {
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .build()
    }

    @Test
    @Throws(Exception::class)
    fun createTask_shouldReturnJsonWhenValidTaskIsPassed_200OK() {
        val jsonTask = "[{\"name\":\"Test\",\"nth\":10}]"
        mvc!!.perform(
            MockMvcRequestBuilders.post("/tasks").content(jsonTask)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
    }

    @Test
    @Throws(Exception::class)
    fun createTask_shouldReturn400WhenNoTaskIsPassed() {
        mvc!!.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    @Test
    @Throws(Exception::class)
    fun createTask_shouldReturn400WhenInvalidJsonIsPassed() {
        val invalidJson = "{name:Test}"
        mvc!!.perform(
            MockMvcRequestBuilders.post("/tasks").content(invalidJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    @Test
    @Throws(Exception::class)
    fun tasks_shouldReturnListAllTasks() {
        mvc!!.perform(
            MockMvcRequestBuilders.post("/tasks").content("[{\"name\":\"Test\",\"nth\":10}]")
                .contentType(MediaType.APPLICATION_JSON)
        )
        mvc!!.perform(MockMvcRequestBuilders.get("/tasks").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json("[{\"name\":\"Test\",\"nth\":10}]"))
    }
}