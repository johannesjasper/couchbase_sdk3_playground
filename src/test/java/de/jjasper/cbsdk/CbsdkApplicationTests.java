package de.jjasper.cbsdk;

import de.jjasper.cbsdk.data.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static de.jjasper.cbsdk.Helpers.*;

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

        var updatedPerson = person.withHomeTown("Hamburg");
        log.info("Updating Person {}", updatedPerson);
        personService.update(updatedPerson);
        log.info("Reading updated Person {}", personService.read(personId));

        personService.delete(personId);
    }
}