package com.senac.viverbem.service;

import com.senac.viverbem.TestDataUtil;
import com.senac.viverbem.domain.address.dto.AddressDTO;
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
public class AddressServiceIntegrationTest {

    private AddressService underTest;

    @Autowired
    public AddressServiceIntegrationTest(AddressService underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatAddressCanBeCreatedAndRecalled(){
        AddressDTO address = TestDataUtil.createTestAddressA();
        underTest.createAddress(address);
        Optional<AddressDTO> result = underTest.findAddressById(address.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(address);
    }

    @Test
    public void testThatMultipleAddressesCanBeCreatedAndRecalled(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        underTest.createAddress(addressA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        underTest.createAddress(addressB);
        AddressDTO addressC = TestDataUtil.createTestAddressC();
        underTest.createAddress(addressC);

        List<AddressDTO> result = underTest.getAllAddresses();
        assertThat(result)
                .hasSize(3)
                .containsOnly(addressA, addressB, addressC);

    }

    @Test
    public void testThatAddressCanBeUpdated(){
        AddressDTO address = TestDataUtil.createTestAddressA();
        underTest.createAddress(address);
        address.setStreet("Updated street");
        underTest.createAddress(address);
        Optional<AddressDTO> result = underTest.findAddressById(address.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getStreet()).isEqualTo("Updated street");
        assertThat(result.get()).isEqualTo(address);
    }

    @Test
    public void testThatAddressCanBeCreatedAndDeleted(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        underTest.createAddress(addressA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        underTest.createAddress(addressB);

        underTest.deleteAddress(addressA.getId());

        List<AddressDTO> result = underTest.getAllAddresses();
        assertThat(result)
                .hasSize(1)
                .containsOnly(addressB);

    }
}
