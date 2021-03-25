package fr.esiea.ex4A.model;

public class User {
        private String userEmail;
        private String userName;
        private String userTweeter;
        private String userCountry;
        private String userSex;
        private String userSexPref;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTweeter() {
        return userTweeter;
    }

    public void setUserTweeter(String userTweeter) {
        this.userTweeter = userTweeter;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserSexPref() {
        return userSexPref;
    }

    public void setUserSexPref(String userSexPref) {
        this.userSexPref = userSexPref;
    }
}

