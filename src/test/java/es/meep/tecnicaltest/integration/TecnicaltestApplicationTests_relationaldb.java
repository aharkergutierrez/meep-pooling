package es.meep.tecnicaltest.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"core","relationaldb"})
class TecnicaltestApplicationTests_relationaldb {

	@Test
	void contextLoads() {
	}

}
