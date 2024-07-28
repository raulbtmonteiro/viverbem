package com.senac.viverbem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.viverbem.TestDataUtil;
import com.senac.viverbem.domain.address.AddressModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AddressControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public AddressControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testThatListAddressesReturnsHttp403WhenNotAuthenticated() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/addresses")
        ).andExpect(
                MockMvcResultMatchers.status().isForbidden()
        );
    }

//    @Test
//    void testThatCreateAddressReturnsHttp201() throws Exception {
//        AddressModel address = TestDataUtil.createTestAddressA();
//        address.setId(null);
//        String addressJson = objectMapper.writeValueAsString(address);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/addresses")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(addressJson)
//        ).andExpect(
//                MockMvcResultMatchers.status().isCreated()
//        );
//    }
}
