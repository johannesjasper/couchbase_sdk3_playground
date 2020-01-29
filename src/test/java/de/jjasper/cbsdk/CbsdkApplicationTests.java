package de.jjasper.cbsdk;

import static de.jjasper.cbsdk.Helpers.uuid;

import de.jjasper.cbsdk.person.PersonService;
import de.jjasper.cbsdk.person.data.Person;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class CbsdkApplicationTests {

    @Autowired
    private PersonService personService;

    @Test
    void crud() {
        var personId = uuid();
        var person = new Person(personId,
                                LocalDate.of(1991, 1, 1),
                                "Peter Pan",
                                "Berlin");

        log.info("Inserting Person {}", person);
        personService.create(person);
        log.info("Reading Person {}", personService.read(personId));
        log.info("Reading Person name {}", personService.getProjecion(personId));

        log.info("Reading list of Persons {}", personService.getByName(person.getName()));
        log.info("Reading list of Person names {}", personService.getProjecionByName(person.getName()));

        var updatedPerson = person.withHomeTown("Hamburg");
        log.info("Updating Person {}", updatedPerson);
        personService.update(updatedPerson);
        log.info("Reading updated Person {}", personService.read(personId));

        personService.delete(personId);
    }
}
