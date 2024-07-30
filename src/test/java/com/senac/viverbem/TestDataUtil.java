package com.senac.viverbem;

import com.senac.viverbem.domain.activity.dto.ActivityPostDTO;
import com.senac.viverbem.domain.address.dto.AddressDTO;
import com.senac.viverbem.domain.user.dto.UserDTO;
import com.senac.viverbem.domain.user.dto.UserPostDTO;
import com.senac.viverbem.infra.security.TokenService;
import com.senac.viverbem.mappers.impl.UserMapper;
import com.senac.viverbem.service.UserService;

public class TestDataUtil {

    private TestDataUtil(){
    }

    public static AddressDTO createTestAddressA(){
        return AddressDTO.builder()
                .id(1l)
                .street("Avenida Afonso Pena, 1500")
                .neighborhood("Vila Guilherme")
                .postal_code("01.221-201")
                .city("São Paulo")
                .state("São Paulo")
                .country("Brasil")
                .build();
    }

    public static AddressDTO createTestAddressB(){
        return AddressDTO.builder()
                .id(2l)
                .street("Avenida Cristiano Machado, 310")
                .neighborhood("Vila Mariana")
                .postal_code("34.467-310")
                .city("Campinas")
                .state("São Paulo")
                .country("Brasil")
                .build();
    }

    public static AddressDTO createTestAddressC(){
        return AddressDTO.builder()
                .id(3l)
                .street("Rua da Guaíra, 40")
                .neighborhood("Centro")
                .postal_code("23.450-020")
                .city("Cabo Frio")
                .state("Rio de Janeiro")
                .country("Brasil")
                .build();
    }

    public static AddressDTO createTestAddressD(){
        return AddressDTO.builder()
                .id(4l)
                .street("Rua da Guaíra, 40")
                .neighborhood("Centro")
                .postal_code("23.450-020")
                .city("Cabo Frio")
                .state("Rio de Janeiro")
                .country("Brasil")
                .build();
    }

    public static AddressDTO createTestAddressE(){
        return AddressDTO.builder()
                .id(5l)
                .street("Rua da Guaíra, 40")
                .neighborhood("Centro")
                .postal_code("23.450-020")
                .city("Cabo Frio")
                .state("Rio de Janeiro")
                .country("Brasil")
                .build();
    }

    public static AddressDTO createTestAddressF(){
        return AddressDTO.builder()
                .id(6l)
                .street("Rua da Guaíra, 40")
                .neighborhood("Centro")
                .postal_code("23.450-020")
                .city("Cabo Frio")
                .state("Rio de Janeiro")
                .country("Brasil")
                .build();
    }

    public static UserPostDTO createTestUserA(AddressDTO address){
        return UserPostDTO.builder()
                .id(1l)
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

    public static UserPostDTO createTestUserB(AddressDTO address){
        return UserPostDTO.builder()
                .id(2l)
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

    public static UserPostDTO createTestUserC(AddressDTO address){
        return UserPostDTO.builder()
                .id(3l)
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

    public static ActivityPostDTO createTestActivityA(AddressDTO address, Long user){
        return ActivityPostDTO.builder()
                .id(1l)
                .title("First Activity")
                .description("description test 1")
                .local(address)
                .owner(user)
                .build();
    }

    public static ActivityPostDTO createTestActivityB(AddressDTO address, Long user) {
        return ActivityPostDTO.builder()
                .id(2l)
                .title("Second Activity")
                .description("description test 2")
                .local(address)
                .owner(user)
                .build();
    }

    public static ActivityPostDTO createTestActivityC(AddressDTO address, Long user) {
        return ActivityPostDTO.builder()
                .id(3l)
                .title("Third Activity")
                .description("description test 3")
                .local(address)
                .owner(user)
                .build();
    }

    public static String generateTestToken(UserService userService, TokenService tokenService, UserMapper userMapper) {
        AddressDTO address = TestDataUtil.createTestAddressA();
        UserPostDTO user = TestDataUtil.createTestUserA(address);
        userService.saveUser(user);
        UserDTO userDTO = new UserDTO(user);
        String token = tokenService.generateToken(userMapper.mapFrom(userDTO));
        return token;
    }
}
