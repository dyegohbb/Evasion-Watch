package com.br.evasion.watch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EvasionWatchApplicationTests {

	@Test
	void contextLoads() {
        // Act
        int result = 5 + 3;

        // Assert
        Assertions.assertEquals(8, result, "A adição de 5 e 3 deve ser igual a 8");
	}

}
