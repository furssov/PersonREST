package furssov.com.ua.personrest.service;

import furssov.com.ua.personrest.dto.PersonDto;
import furssov.com.ua.personrest.model.Person;
import furssov.com.ua.personrest.repository.PersonRepository;
import furssov.com.ua.personrest.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith({SpringExtension.class})
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
        people.add(new Person(3,"D", "E", LocalDate.now()));

        Mockito.when(personRepository.findAll()).thenReturn(people);

        List<PersonDto> personList = new ArrayList<>();
        personList.add(new PersonDto("A", "B",0));
        personList.add(new PersonDto("B", "C",0));
        personList.add(new PersonDto("D", "E",0));
        Assertions.assertEquals(personList, personService.findAll());

    }
}
