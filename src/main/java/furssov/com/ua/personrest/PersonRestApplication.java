package furssov.com.ua.personrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class PersonRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonRestApplication.class, args);
    }

}
