package com.senac.viverbem.service;

import com.senac.viverbem.TestDataUtil;
import com.senac.viverbem.domain.address.dto.AddressDTO;
import com.senac.viverbem.domain.user.dto.UserDTO;
import com.senac.viverbem.domain.user.dto.UserPostDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class UserServiceIntegrationTest {

    private UserService underTest;

    @Autowired
    public UserServiceIntegrationTest(UserService underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatUserCanBeCreatedAndRecalled(){
        AddressDTO address = TestDataUtil.createTestAddressA();
        UserPostDTO user = TestDataUtil.createTestUserA(address);
        underTest.saveUser(user);
        Optional<UserDTO> result = underTest.getUserById(user.getId());
        UserDTO sentUser = new UserDTO(user);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(sentUser);
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
        UserPostDTO userA = TestDataUtil.createTestUserA(addressA);
        underTest.saveUser(userA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        UserPostDTO userB = TestDataUtil.createTestUserB(addressB);
        underTest.saveUser(userB);
        AddressDTO addressC = TestDataUtil.createTestAddressC();
        UserPostDTO userC = TestDataUtil.createTestUserC(addressC);
        underTest.saveUser(userC);

        List<UserDTO> result = underTest.getAllUsers();

        UserDTO sentUserA = new UserDTO(userA);
        UserDTO sentUserB = new UserDTO(userB);
        UserDTO sentUserC = new UserDTO(userC);

        assertThat(result)
                .hasSize(3)
                .containsOnly(sentUserA, sentUserB, sentUserC);

    }
//
    @Test
    public void testThatUserCanBeUpdated(){
        AddressDTO address = TestDataUtil.createTestAddressA();
        UserPostDTO user = TestDataUtil.createTestUserA(address);
        underTest.saveUser(user);
        user.setLastname("Updated last name");
        underTest.saveUser(user);
        UserDTO sentUser = new UserDTO(user);
        Optional<UserDTO> result = underTest.getUserById(user.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getLastname()).isEqualTo("Updated last name");
        assertThat(result.get()).isEqualTo(sentUser);
    }

    @Test
    public void testThatUserCanBeCreatedAndDeleted(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        UserPostDTO userA = TestDataUtil.createTestUserA(addressA);
        underTest.saveUser(userA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        UserPostDTO userB = TestDataUtil.createTestUserB(addressB);
        underTest.saveUser(userB);

        UserDTO sentUserB = new UserDTO(userB);

        underTest.deleteUser(userA.getId());

        List<UserDTO> result = underTest.getAllUsers();
        assertThat(result)
                .hasSize(1)
                .containsOnly(sentUserB);

    }
}
