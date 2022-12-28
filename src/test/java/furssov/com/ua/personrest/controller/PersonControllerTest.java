package furssov.com.ua.personrest.controller;


import furssov.com.ua.personrest.model.Person;
import furssov.com.ua.personrest.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonService personService;



    @Test
    void addPerson() throws Exception {

        Person person = new Person();

        person.setName("A");
        person.setSecondName("B");
        person.setDateOfBirth(LocalDate.of(2003, 10, 06));



        personService.save(person);

        mockMvc
                .perform(post("/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(
                                """
                               {
                                   "name" : "A",
                                   "secondName" : "B",
                                   "dateOfBirth" :  "2004-10-10"                                 
                               
                               }""".stripIndent()
                               )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("A"))
                .andExpect(jsonPath("$.secondName").value("B"));

    }

    @Test
    void findById() throws Exception {

        Person person = new Person();

        person.setName("A");
        person.setSecondName("B");
        person.setDateOfBirth(LocalDate.of(2003, 10, 06));


        Person person1 = new Person();

        person1.setName("C");
        person1.setSecondName("D");
        person1.setDateOfBirth(LocalDate.of(2004, 8, 11));

        personService.save(person);
        personService.save(person1);

        mockMvc.perform(get("/people/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

        )  .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("A"))
                .andExpect(jsonPath("$.secondName").value("B"))
                .andExpect(jsonPath("$.age").value(19));

        mockMvc.perform(get("/people/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

                )  .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("C"))
                .andExpect(jsonPath("$.secondName").value("D"))
                .andExpect(jsonPath("$.age").value(18));


    }

    @Test
    void findAll() throws Exception {

        Person person = new Person();

        person.setName("A");
        person.setSecondName("B");
        person.setDateOfBirth(LocalDate.of(2003, 10, 06));


        Person person1 = new Person();

        person1.setName("C");
        person1.setSecondName("D");
        person1.setDateOfBirth(LocalDate.of(2004, 8, 11));

        personService.save(person);
        personService.save(person1);

        mockMvc.perform(get("/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

                )  .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("A"))
                .andExpect(jsonPath("$[0].secondName").value("B"))
                .andExpect(jsonPath("$[1].age").value(18));




    }
}
