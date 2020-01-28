package de.jjasper.cbsdk.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

import static lombok.AccessLevel.*;

@Value
@With
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
public class PersonNameProjection {

    private @NonNull String id;
    private @NonNull String name;

}