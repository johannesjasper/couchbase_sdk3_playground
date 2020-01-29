package de.jjasper.cbsdk.person;

import static com.couchbase.client.java.manager.query.CreateQueryIndexOptions.createQueryIndexOptions;
import static com.couchbase.client.java.query.QueryOptions.queryOptions;
import static com.couchbase.client.java.query.QueryScanConsistency.NOT_BOUNDED;
import static de.jjasper.cbsdk.dao.BucketService.TEST_BUCKET;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonObject;
import de.jjasper.cbsdk.person.data.Person;
import de.jjasper.cbsdk.person.data.PersonNameProjection;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    @Qualifier(TEST_BUCKET)
    private final Bucket bucket;
    private final Cluster cluster;

    @PostConstruct
    private void createIndices() {
        cluster.queryIndexes().createIndex(
            bucket.name(), "person_name", List.of("name"),
            createQueryIndexOptions().ignoreIfExists(true));
    }


    public void create(Person person) {
        bucket.defaultCollection().insert(person.getId(), person);
    }

    public Person read(String id) {
        return bucket.defaultCollection().get(id).contentAs(Person.class);
    }

    public List<Person> getByName(String name) {
        return cluster.query(String.format("select b.* from %s b where `name`=$name", bucket.name()),
                             queryOptions()
                                 .parameters(JsonObject.create().put("name", name))
                                 .scanConsistency(NOT_BOUNDED))
                      .rowsAs(Person.class);
    }

    public List<PersonNameProjection> getProjecionByName(String name) {
        return cluster.query(String.format("select b.name from %s b where `name`=$name", bucket.name()),
                             queryOptions()
                                 .parameters(JsonObject.create().put("name", name))
                                 .scanConsistency(NOT_BOUNDED))
                      .rowsAs(PersonNameProjection.class);
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
