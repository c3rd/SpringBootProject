package dev.c3rd.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.datasource.url=jdbc:mysql://localhost:3309/mytrip")
class AppApplicationTests {

	@Test
	void contextLoads() {
	}

}
