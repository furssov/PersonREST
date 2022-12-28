package furssov.com.ua.personrest.repository;


import furssov.com.ua.personrest.dto.PersonDto;
import furssov.com.ua.personrest.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void repoTest()
    {
        int size =  personRepository.findAll().size();
       Person person = new Person();

       person.setName("ABC");
       person.setSecondName("BCA");
       person.setDateOfBirth(LocalDate.now());
       personRepository.save(person);

       size++;
       Assertions.assertEquals(size, personRepository.findAll().size());




    }

}
