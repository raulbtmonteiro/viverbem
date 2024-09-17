package com.senac.viverbem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.viverbem.TestDataUtil;
import com.senac.viverbem.domain.address.dto.AddressDTO;
import com.senac.viverbem.infra.security.TokenService;
import com.senac.viverbem.mappers.impl.UserMapper;
import com.senac.viverbem.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AddressControllerIntegrationTest {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserMapper userMapper;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private UserService userService;

    @Autowired
    public AddressControllerIntegrationTest(MockMvc mockMvc, UserService userService) {
        this.mockMvc = mockMvc;
        this.userService = userService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testThatCreateAddressReturnsHttp201() throws Exception {
        String token = TestDataUtil.generateTestToken(userService,tokenService,userMapper);

        AddressDTO address = TestDataUtil.createTestAddressA();
        String addressJson = objectMapper.writeValueAsString(address);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .content(addressJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }
}
