package org.jetbrains.test.multids.vet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class VetControllerTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.0-alpine");

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:9.1.0");

    @DynamicPropertySource
    static void dynamicPropertyRegistrar(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.ds1.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.ds1.username", postgres::getUsername);
        registry.add("spring.datasource.ds1.password", postgres::getPassword);

        registry.add("spring.datasource.ds2.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.ds2.username", mysql::getUsername);
        registry.add("spring.datasource.ds2.password", mysql::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Find an existing vet by ID")
    void testFindByIdExisting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vets/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Dr. John Doe"))
                .andReturn();
    }


    @Test
    @DisplayName("Find an non-existing vet by ID")
    void testFindByIdNonExisting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vets/{id}", 10L))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }
}
