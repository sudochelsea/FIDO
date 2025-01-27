package Base;

import org.junit.jupiter.api.BeforeAll;
import static config.RestAssuredConfig.setup;
public class BaseTests {

        @BeforeAll
        public static void initialize() {
            setup();
        }
    }

