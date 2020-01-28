package de.jjasper.cbsdk;

import com.couchbase.client.java.Bucket;
import de.jjasper.cbsdk.data.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static de.jjasper.cbsdk.dao.BucketService.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonCrudService {

    @Autowired
    @Qualifier(PERSON_BUCKET)
    private Bucket bucket;

    public void create(Person person) {
        bucket.defaultCollection().insert(person.getId(), person);
    }

    public Person read(String id) {
        return bucket.defaultCollection().get(id).contentAs(Person.class);
    }

    public Person readFromReplica(String id) {
        return bucket.defaultCollection().getAnyReplica(id).contentAs(Person.class);
    }

    public void update(Person person) {
        bucket.defaultCollection().upsert(person.getId(), person);
    }

    public void delete(String id) {
        bucket.defaultCollection().remove(id);
    }

    public boolean exists(String id) {
        return bucket.defaultCollection().exists(id).exists();
    }
}