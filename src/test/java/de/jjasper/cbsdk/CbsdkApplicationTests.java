package de.jjasper.cbsdk;

import de.jjasper.cbsdk.data.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static de.jjasper.cbsdk.Helpers.*;

@SpringBootTest
@Slf4j
class CbsdkApplicationTests {

    @Autowired
    private PersonCrudService personCrudService;

    @Test
    void contextLoads() {
        var personId = uuid();
        var person = new Person(personId, "type", "name", "Berlin");
        log.info("Inserting Person {}", person);
        personCrudService.create(person);
        log.info("Reading Person {}", personCrudService.read(personId));

        var updatedPerson = person.withHomeTown("Hamburg");
        log.info("Updating Person {}", updatedPerson);
        personCrudService.update(updatedPerson);
        log.info("Reading Person {}", personCrudService.read(personId));

        personCrudService.delete(personId);
    }
}