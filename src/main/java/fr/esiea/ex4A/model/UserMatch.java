package fr.esiea.ex4A.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UserMatch {
    @JsonProperty("userName")
    public final String name;
    @JsonProperty ("userTweeter")
    public final String twitter;
    @JsonProperty ("userAge")
    public final int userAge;

    public UserMatch(String name, String twitter, int userAge) {
        this.name = name;
        this.twitter = twitter;
        this.userAge = userAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMatch userMatch = (UserMatch) o;
        return Objects.equals(name, userMatch.name) && Objects.equals(twitter, userMatch.twitter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, twitter);
    }

    @Override
    public String toString() {
        return "UserMatch{" +
            "name='" + name + '\'' +
            ", twitter='" + twitter + '\'' +
            '}';
    }
}
