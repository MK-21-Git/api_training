package fr.esiea.ex4A.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UserInfo {

    public final String userEmail;
    public final String userName;
    public final String userTweeter;
    public final String userCountry;
    public final String userSex;
    public final String userSexPref;
    public final int userAge;

    public UserInfo(AgifyUser user, String userSex, String userSexPref) {
        this(user.name + "@titi.com", user.name.substring(0, 1).toUpperCase() + user.name.substring(1), user.name, user.countryId, userSex, userSexPref, user.age);
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserInfo(@JsonProperty ("userEmail") String userEmail, @JsonProperty ("userName") String userName, @JsonProperty ("userTweeter") String userTweeter, @JsonProperty ("userCountry") String userCountry, @JsonProperty ("userSex") String userSex, @JsonProperty ("userSexPref") String userSexPref, @JsonProperty ("userAge") int userAge) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userTweeter = userTweeter;
        this.userCountry = userCountry;
        this.userSex = userSex;
        this.userSexPref = userSexPref;
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public String getUserName() {
        return userName;
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
    public int getUserAge() {
        return userAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return userName.equals(userInfo.userName) && userCountry.equals(userInfo.userCountry);
    }

    public boolean isMatch(UserInfo userInfo) {
        return !this.equals(userInfo) && this.userSexPref.equals(userInfo.userSex)
            && this.userSex.equals(userInfo.userSexPref)
            && userInfo.getUserAge() >= this.userAge - 4 && userInfo.userAge <= this.userAge + 4;
    }

    public UserMatch asMatch() {
        return new UserMatch(userName, userTweeter, userAge);
    }
}

