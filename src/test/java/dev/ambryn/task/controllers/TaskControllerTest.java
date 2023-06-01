package dev.ambryn.task.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The task controller should allow :
 * - to create a new async task and return its handler
 * - to follow the progress of the task via its handler
 * - to delete a task via its handler
 * - to get the result one finished
 */
@SpringBootTest
class TaskControllerTest {

    MockMvc mvc;

    @BeforeEach
    public void setup(WebApplicationContext context) {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                                  .build();
    }

    @Test
    public void createTask_shouldReturnJsonWhenValidTaskIsPassed_200OK() throws Exception {
        String jsonTask = "[{\"name\":\"Test\",\"nth\":10}]";
        this.mvc.perform(post("/tasks").content(jsonTask)
                                       .contentType(MediaType.APPLICATION_JSON)
                                       .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createTask_shouldReturn400WhenNoTaskIsPassed() throws Exception {
        this.mvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createTask_shouldReturn400WhenInvalidJsonIsPassed() throws Exception {
        String invalidJson = "{name:Test}";
        this.mvc.perform(post("/tasks").content(invalidJson)
                                       .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getTasks_shouldReturnListAllTasks() throws Exception {
        this.mvc.perform(post("/tasks").content("[{\"name\":\"Test\",\"nth\":10}]")
                                       .contentType(MediaType.APPLICATION_JSON));
        this.mvc.perform(get("/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"name\":\"Test\",\"nth\":10}]"));
    }
}