package de.jjasper.cbsdk;

import java.util.UUID;

public class Helpers {
    private Helpers() { }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}
