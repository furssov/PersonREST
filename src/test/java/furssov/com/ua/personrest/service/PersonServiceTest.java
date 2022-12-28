package furssov.com.ua.personrest.service;

import furssov.com.ua.personrest.dto.PersonDto;
import furssov.com.ua.personrest.exceptions.NoSuchPersonException;
import furssov.com.ua.personrest.model.Person;
import furssov.com.ua.personrest.repository.PersonRepository;
import furssov.com.ua.personrest.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

@ExtendWith({SpringExtension.class})
@SpringBootTest
public class PersonServiceTest {

    @TestConfiguration
    static class PersonServiceConfig
    {
        @Bean
        public PersonService personService()
        {
            return new PersonServiceImpl();
        }
    }
    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    void save()
    {
        Person person = new Person();
        person.setName("Dmitry");
        person.setSecondName("Ivanov");
        person.setDateOfBirth(LocalDate.now());

        Mockito.when(personRepository.save(person)).thenReturn(person);

        Person db = personService.save(person);
        Assertions.assertEquals(person, db);
    }

    @Test
    void findAll()
    {
        List<Person> people= new ArrayList<>();
        people.add(new Person(1,"A", "B", LocalDate.now()));
        people.add(new Person(2,"B", "C", LocalDate.now()));
        people.add(new Person(3,"D", "E", LocalDate.of(2003, 10 , 06)));

        Mockito.when(personRepository.findAll()).thenReturn(people);

        List<PersonDto> personList = new ArrayList<>();
        personList.add(new PersonDto("A", "B",0));
        personList.add(new PersonDto("B", "C",0));
        personList.add(new PersonDto("D", "E",19));
        Assertions.assertEquals(personList, personService.findAll());

    }

    @Test()
    void findById() throws NoSuchPersonException {
        Person person = new Person(1,"A", "B", LocalDate.now());
        Mockito.when(personRepository.findById(1l)).thenReturn(Optional.of(person));


        PersonDto expected = new PersonDto("A", "B", 0);

        Assertions.assertEquals(expected, personService.findById(1l));






    }
}
