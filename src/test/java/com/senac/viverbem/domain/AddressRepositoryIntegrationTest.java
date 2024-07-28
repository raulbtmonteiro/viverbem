package com.senac.viverbem.domain;

import com.senac.viverbem.TestDataUtil;
import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.address.AddressRepository;
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
public class AddressRepositoryIntegrationTest {

    private AddressRepository underTest;

    @Autowired
    public AddressRepositoryIntegrationTest(AddressRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatAddressCanBeCreatedAndRecalled(){
        AddressModel address = TestDataUtil.createTestAddressA();
        underTest.save(address);
        Optional<AddressModel> result = underTest.findById(address.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(address);
    }

    @Test
    public void testThatMultipleAddressesCanBeCreatedAndRecalled(){
        AddressModel addressA = TestDataUtil.createTestAddressA();
        underTest.save(addressA);
        AddressModel addressB = TestDataUtil.createTestAddressB();
        underTest.save(addressB);
        AddressModel addressC = TestDataUtil.createTestAddressC();
        underTest.save(addressC);

        List<AddressModel> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsOnly(addressA, addressB, addressC);

    }

    @Test
    public void testThatAddressCanBeUpdated(){
        AddressModel address = TestDataUtil.createTestAddressA();
        underTest.save(address);
        address.setStreet("Updated street");
        underTest.save(address);
        Optional<AddressModel> result = underTest.findById(address.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getStreet()).isEqualTo("Updated street");
        assertThat(result.get()).isEqualTo(address);
    }

    @Test
    public void testThatAddressCanBeCreatedAndDeleted(){
        AddressModel addressA = TestDataUtil.createTestAddressA();
        underTest.save(addressA);
        AddressModel addressB = TestDataUtil.createTestAddressB();
        underTest.save(addressB);

        underTest.deleteById(addressA.getId());

        List<AddressModel> result = underTest.findAll();
        assertThat(result)
                .hasSize(1)
                .containsOnly(addressB);

    }
}
