package de.jjasper.cbsdk.dao;

import com.couchbase.client.core.env.TimeoutConfig;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.env.ClusterEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.couchbase.client.java.ClusterOptions.*;

@Component
@RequiredArgsConstructor
public class BucketService {

    private final BucketConfig bucketConfig;

    public static final String PERSON_BUCKET = "person";

    private Cluster cluster;
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Bean(name = PERSON_BUCKET)
    public Bucket getTestBucket() {
        return openBucket(bucketConfig.getBucketName());
    }

    @PostConstruct
    private void init() {
        ClusterEnvironment environment = ClusterEnvironment
            .builder()
            .timeoutConfig(TimeoutConfig.kvTimeout(Duration.ofSeconds(2)))
            .build();
        cluster = Cluster.connect(
            bucketConfig.getHost(),
            clusterOptions(bucketConfig.getUserName(), bucketConfig.getPassword()).environment(environment));
    }

    synchronized private Bucket openBucket(String name) {
        if (!buckets.containsKey(name)) {
            buckets.put(name, cluster.bucket(name));
        }
        return buckets.get(name);
    }
}