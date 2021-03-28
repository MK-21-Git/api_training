package fr.esiea.ex4A.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UserData {

        public final String userEmail;
        public final String userName;
        public final String userTweeter;
        public final String userCountry;
        public final String userSex;
        public final String userSexPref;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserData( @JsonProperty ("userEmail") String userEmail,
                     @JsonProperty ("userName") String userName,
                     @JsonProperty ("userTweeter") String userTweeter,
                     @JsonProperty ("userCountry") String userCountry,
                     @JsonProperty ("userSex") String userSex,
                     @JsonProperty ("userSexPref") String userSexPref) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userTweeter = userTweeter;
        this.userCountry = userCountry;
        this.userSex = userSex;
        this.userSexPref = userSexPref;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserTweeter() {
        return userTweeter;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public String getUserSex() {
        return userSex;
    }

    public String getUserSexPref() {
        return userSexPref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return userName.equals(userData.userName) && userCountry.equals(userData.userCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userCountry);
    }
}

