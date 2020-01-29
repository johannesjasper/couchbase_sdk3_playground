package de.jjasper.cbsdk.person.data;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value
@With
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
public class Person {

    private @NonNull String id;
    private @NonNull LocalDate dateOfBirth;
    private @NonNull String name;
    private @NonNull String homeTown;

}
