package de.jjasper.cbsdk.person.data;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value
@With
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
public class PersonNameProjection {

    private @NonNull String name;

}
