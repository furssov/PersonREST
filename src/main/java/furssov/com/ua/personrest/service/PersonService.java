package furssov.com.ua.personrest.service;

import furssov.com.ua.personrest.dto.PersonDto;
import furssov.com.ua.personrest.exceptions.NoSuchPersonException;
import furssov.com.ua.personrest.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person save(Person person);
    List<PersonDto> findAll();

    PersonDto findById(long id) throws NoSuchPersonException;
}
