package com.senac.viverbem.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.viverbem.TestDataUtil;
import com.senac.viverbem.domain.address.AddressDTO;
import com.senac.viverbem.domain.user.AuthDTO;
import com.senac.viverbem.domain.user.UserDTO;
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
public class AuthControllerIntegrationTeste {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthControllerIntegrationTeste(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    public final UserDTO createUser() throws Exception {
        AddressDTO address = TestDataUtil.createTestAddressA();
        UserDTO user = TestDataUtil.createTestUserA(address);

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
        );

        return user;
    }

    @Test
    void testThatCreatedUsersCanAuthenticate() throws Exception {
        UserDTO user = createUser();
        AuthDTO loginBodyRequest = new AuthDTO(user.getEmail(),user.getPassword());
        String loginBodyRequestJson = objectMapper.writeValueAsString(loginBodyRequest);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginBodyRequestJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
}
