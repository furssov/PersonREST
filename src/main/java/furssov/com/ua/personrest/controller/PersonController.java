package furssov.com.ua.personrest.controller;

import furssov.com.ua.personrest.dto.PersonDto;
import furssov.com.ua.personrest.exceptions.NoSuchPersonException;
import furssov.com.ua.personrest.model.Person;
import furssov.com.ua.personrest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping
    public ResponseEntity<List<PersonDto>> getAll()
    {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getById(@PathVariable long id) throws NoSuchPersonException {
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }
}
