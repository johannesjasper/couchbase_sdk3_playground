package de.jjasper.cbsdk.dao;

import com.couchbase.client.core.env.TimeoutConfig;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.codec.JacksonJsonSerializer;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.couchbase.client.java.ClusterOptions.*;

@Component
@RequiredArgsConstructor
public class BucketService {

    private final BucketConfig bucketConfig;

    public static final String TEST_BUCKET = "test";


    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper().findAndRegisterModules();
    }

    @Bean(name = TEST_BUCKET)
    public Bucket getTestBucket(Cluster cluster) {
        return cluster.bucket(bucketConfig.getBucketName());
    }

    @Bean
    public Cluster getCluster(ObjectMapper objectMapper) {
        ClusterEnvironment environment = ClusterEnvironment
            .builder()
            .jsonSerializer(JacksonJsonSerializer.create(objectMapper))
            .timeoutConfig(TimeoutConfig.kvTimeout(Duration.ofSeconds(2)))
            .build();
        return Cluster.connect(
            bucketConfig.getHost(),
            clusterOptions(bucketConfig.getUserName(), bucketConfig.getPassword()).environment(environment));
    }
}