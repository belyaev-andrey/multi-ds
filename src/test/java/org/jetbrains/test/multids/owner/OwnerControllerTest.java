package org.jetbrains.test.multids.owner;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class OwnerControllerTest {

    private static final Logger log = LoggerFactory.getLogger(OwnerControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/owners/{id}", 1L)).andReturn();
        log.info(mvcResult.getResponse().getContentAsString());
    }


}
