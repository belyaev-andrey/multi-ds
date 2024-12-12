package org.jetbrains.test.multids.owner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Find an existing owner by ID")
    void testFindByIdExisting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/owners/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andReturn();
    }

    @Test
    @DisplayName("Find an non-existing owner by ID")
    void testFindByIdNonExisting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/owners/{id}", 10L))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

}
