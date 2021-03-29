package fr.esiea.ex4A.model;

import java.util.Objects;

public class Key {
    private final String name;
    private final String country;

    public Key(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return name.equals(key.name) && country.equals(key.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}
