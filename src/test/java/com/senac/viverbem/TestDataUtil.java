package com.senac.viverbem;

import com.senac.viverbem.domain.activity.ActivityModel;
import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.user.UserModel;

public class TestDataUtil {

    private TestDataUtil(){
    }

    public static AddressModel createTestAddressA(){
        return AddressModel.builder()
                .street("Avenida Afonso Pena, 1500")
                .neighborhood("Vila Guilherme")
                .postal_code("01.221-201")
                .city("São Paulo")
                .state("São Paulo")
                .country("Brasil")
                .build();
    }

    public static AddressModel createTestAddressB(){
        return AddressModel.builder()
                .street("Avenida Cristiano Machado, 310")
                .neighborhood("Vila Mariana")
                .postal_code("34.467-310")
                .city("Campinas")
                .state("São Paulo")
                .country("Brasil")
                .build();
    }

    public static AddressModel createTestAddressC(){
        return AddressModel.builder()
                .street("Rua da Guaíra, 40")
                .neighborhood("Centro")
                .postal_code("23.450-020")
                .city("Cabo Frio")
                .state("Rio de Janeiro")
                .country("Brasil")
                .build();
    }

    public static UserModel createTestUserA(AddressModel address){
        return UserModel.builder()
                .cpf("123.456.789-01")
                .firstname("John")
                .lastname("Smith")
                .email("johnsmith@test.com")
                .gender("male")
                .phone("1199999999")
                .password("password")
                .address(address)
                .build();
    }

    public static UserModel createTestUserB(AddressModel address){
        return UserModel.builder()
                .cpf("987.654.321-02")
                .firstname("Ana")
                .lastname("Simpson")
                .email("anasimpson@test.com")
                .gender("female")
                .phone("1198888888")
                .password("password")
                .address(address)
                .build();
    }

    public static UserModel createTestUserC(AddressModel address){
        return UserModel.builder()
                .cpf("427.876.965-03")
                .firstname("George")
                .lastname("David")
                .email("georgedavid@test.com")
                .gender("male")
                .phone("3198888888")
                .password("password")
                .address(address)
                .build();
    }

    public static ActivityModel createTestActivityA(AddressModel address, UserModel user){
        return ActivityModel.builder()
                .title("First Activity")
                .description("description test 1")
                .local(address)
                .owner(user)
                .build();
    }

    public static ActivityModel createTestActivityB(AddressModel address, UserModel user) {
        return ActivityModel.builder()
                .title("Second Activity")
                .description("description test 2")
                .local(address)
                .owner(user)
                .build();
    }

    public static ActivityModel createTestActivityC(AddressModel address, UserModel user) {
        return ActivityModel.builder()
                .title("Third Activity")
                .description("description test 3")
                .local(address)
                .owner(user)
                .build();
    }
}
