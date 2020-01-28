package de.jjasper.cbsdk.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "couchbase")
public class BucketConfig {
    @NotNull private String host;
    @NotNull private String userName;
    @NotNull private String password;
    @NotNull private String bucketName;
}