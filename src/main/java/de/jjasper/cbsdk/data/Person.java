package de.jjasper.cbsdk.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

import static lombok.AccessLevel.*;

@Value
@With
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
public class Person {

    private @NonNull String id;
    private @NonNull String type;
    private @NonNull String name;
    private @NonNull String homeTown;

}