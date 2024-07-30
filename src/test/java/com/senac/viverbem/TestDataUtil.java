package com.senac.viverbem;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.senac.viverbem.domain.activity.ActivityDTO;
import com.senac.viverbem.domain.address.AddressDTO;
import com.senac.viverbem.domain.user.UserDTO;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TestDataUtil {

    private static final String SECRET_KEY = "TESTE";
    private static final long TOKEN_EXPIRATION_TIME_IN_HOURS = 2;

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

    public static UserDTO createTestUserA(AddressDTO address){
        return UserDTO.builder()
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

    public static UserDTO createTestUserB(AddressDTO address){
        return UserDTO.builder()
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

    public static UserDTO createTestUserC(AddressDTO address){
        return UserDTO.builder()
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

    public static ActivityDTO createTestActivityA(AddressDTO address, UserDTO user){
        return ActivityDTO.builder()
                .id(1l)
                .title("First Activity")
                .description("description test 1")
                .local(address)
                .owner(user)
                .build();
    }

    public static ActivityDTO createTestActivityB(AddressDTO address, UserDTO user) {
        return ActivityDTO.builder()
                .id(2l)
                .title("Second Activity")
                .description("description test 2")
                .local(address)
                .owner(user)
                .build();
    }

    public static ActivityDTO createTestActivityC(AddressDTO address, UserDTO user) {
        return ActivityDTO.builder()
                .id(3l)
                .title("Third Activity")
                .description("description test 3")
                .local(address)
                .owner(user)
                .build();
    }

    public static String generateTestToken(String username) {
        return JWT.create()
                .withIssuer("viverbem-auth-api")
                .withSubject(username)
                .withExpiresAt(
                        LocalDateTime.now()
                                .plusHours(TOKEN_EXPIRATION_TIME_IN_HOURS)
                                .toInstant(ZoneOffset.of("-03:00"))
                )
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
}
