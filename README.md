# Couchbase SDK3 Playground

In this repo I play around with Couchbase SDK3. 
Since neither Spring-Data nor Testcontainers support it yet, this requires some manual setup.
* spin up a couchbase using
 ```
docker run -d --name db -p 8091-8096:8091-8096 -p 11210-11211:11210-11211 couchbase:enterprise-6.0.3
```
* configure the cluster admin according to the values in the `application.yml`. 
* create a bucket according to the set values.

Since I want to test the compatibility with older server versions this does not make any use of collections or scopes.