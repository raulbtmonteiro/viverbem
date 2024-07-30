package com.senac.viverbem.service;

import com.senac.viverbem.TestDataUtil;
import com.senac.viverbem.domain.address.AddressDTO;
import com.senac.viverbem.domain.user.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceIntegrationTest {

    private UserService underTest;

    @Autowired
    public UserServiceIntegrationTest(UserService underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatUserCanBeCreatedAndRecalled(){
        AddressDTO address = TestDataUtil.createTestAddressA();
        UserDTO user = TestDataUtil.createTestUserA(address);
        underTest.saveUser(user);
        Optional<UserDTO> result = underTest.getUserById(user.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(user);
    }

//    @Test
//    public void testThatUserCanBeCreatedAndRecalledByEmail() throws JsonProcessingException {
//        AddressDTO address = TestDataUtil.createTestAddressA();
//        UserDTO user = TestDataUtil.createTestUserA(address);
//        underTest.saveUser(user);
//        UserDetails result = underTest.getUserByEmail(user.getEmail());
//        assertThat(result).isNotNull();
//        assertThat(result).isEqualTo(user);
//    }

    @Test
    public void testThatMultipleAddressesCanBeCreatedAndRecalled(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        UserDTO userA = TestDataUtil.createTestUserA(addressA);
        underTest.saveUser(userA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        UserDTO userB = TestDataUtil.createTestUserB(addressB);
        underTest.saveUser(userB);
        AddressDTO addressC = TestDataUtil.createTestAddressC();
        UserDTO userC = TestDataUtil.createTestUserC(addressC);
        underTest.saveUser(userC);

        List<UserDTO> result = underTest.getAllUsers();
        assertThat(result)
                .hasSize(3)
                .containsOnly(userA, userB, userC);

    }

    @Test
    public void testThatUserCanBeUpdated(){
        AddressDTO address = TestDataUtil.createTestAddressA();
        UserDTO user = TestDataUtil.createTestUserA(address);
        underTest.saveUser(user);
        user.setLastname("Updated last name");
        underTest.saveUser(user);
        Optional<UserDTO> result = underTest.getUserById(user.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getLastname()).isEqualTo("Updated last name");
        assertThat(result.get()).isEqualTo(user);
    }

    @Test
    public void testThatUserCanBeCreatedAndDeleted(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        UserDTO userA = TestDataUtil.createTestUserA(addressA);
        underTest.saveUser(userA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        UserDTO userB = TestDataUtil.createTestUserB(addressB);
        underTest.saveUser(userB);

        underTest.deleteUser(userA.getId());

        List<UserDTO> result = underTest.getAllUsers();
        assertThat(result)
                .hasSize(1)
                .containsOnly(userB);

    }
}
