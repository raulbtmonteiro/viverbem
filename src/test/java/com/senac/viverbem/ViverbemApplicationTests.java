package com.senac.viverbem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"DATABASE_URL=${env.DATABASE_URL}"})
class ViverbemApplicationTests {


}
