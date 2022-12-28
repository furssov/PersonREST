package furssov.com.ua.personrest.service.impl;

import furssov.com.ua.personrest.dto.PersonDto;
import furssov.com.ua.personrest.exceptions.NoSuchPersonException;
import furssov.com.ua.personrest.model.Person;
import furssov.com.ua.personrest.repository.PersonRepository;
import furssov.com.ua.personrest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;


    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<PersonDto> findAll() {

        List<Person> people =  personRepository.findAll();
        List<PersonDto> personDtos = new ArrayList<>();

        people.forEach(person -> personDtos.add(new PersonDto(person.getName(), person.getSecondName(), countAge(person))));

        return personDtos;
    }

    @Override
    public PersonDto findById(long id) throws NoSuchPersonException {
        Optional<Person> person =  personRepository.findById(id);

        if (person.isPresent())
        {

            PersonDto personDto = new PersonDto(person.get().getName(), person.get().getSecondName(), countAge(person.get()));
            return personDto;
        }
        else throw new NoSuchPersonException("no such person");
    }

    private int countAge(Person person)
    {
        Period period = Period.between( person.getDateOfBirth(), LocalDate.now());
        return period.getYears();
    }
}
