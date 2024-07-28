package com.senac.viverbem.domain;

import com.senac.viverbem.TestDataUtil;
import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.user.UserModel;
import com.senac.viverbem.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryIntegrationTest {

    private UserRepository underTest;

    @Autowired
    public UserRepositoryIntegrationTest(UserRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatUserCanBeCreatedAndRecalled(){
        AddressModel address = TestDataUtil.createTestAddressA();
        UserModel user = TestDataUtil.createTestUserA(address);
        underTest.save(user);
        Optional<UserModel> result = underTest.findById(user.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(user);
    }

    @Test
    public void testThatUserCanBeCreatedAndRecalledByEmail(){
        AddressModel address = TestDataUtil.createTestAddressA();
        UserModel user = TestDataUtil.createTestUserA(address);
        underTest.save(user);
        UserDetails result = underTest.findByEmail(user.getEmail());
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(user);
    }

    @Test
    public void testThatMultipleAddressesCanBeCreatedAndRecalled(){
        AddressModel addressA = TestDataUtil.createTestAddressA();
        UserModel userA = TestDataUtil.createTestUserA(addressA);
        underTest.save(userA);
        AddressModel addressB = TestDataUtil.createTestAddressB();
        UserModel userB = TestDataUtil.createTestUserB(addressB);
        underTest.save(userB);
        AddressModel addressC = TestDataUtil.createTestAddressC();
        UserModel userC = TestDataUtil.createTestUserC(addressC);
        underTest.save(userC);

        List<UserModel> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsOnly(userA, userB, userC);

    }

    @Test
    public void testThatUserCanBeUpdated(){
        AddressModel address = TestDataUtil.createTestAddressA();
        UserModel user = TestDataUtil.createTestUserA(address);
        underTest.save(user);
        user.setLastname("Updated last name");
        underTest.save(user);
        Optional<UserModel> result = underTest.findById(user.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getLastname()).isEqualTo("Updated last name");
        assertThat(result.get()).isEqualTo(user);
    }

    @Test
    public void testThatUserCanBeCreatedAndDeleted(){
        AddressModel addressA = TestDataUtil.createTestAddressA();
        UserModel userA = TestDataUtil.createTestUserA(addressA);
        underTest.save(userA);
        AddressModel addressB = TestDataUtil.createTestAddressB();
        UserModel userB = TestDataUtil.createTestUserB(addressB);
        underTest.save(userB);

        underTest.deleteById(userA.getId());

        List<UserModel> result = underTest.findAll();
        assertThat(result)
                .hasSize(1)
                .containsOnly(userB);

    }
}
