package fr.esiea.ex4A.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class AgifyData {
    final public String name;
    final public Integer age;
    final public Integer count;
    @SerializedName("country_id")
    final public String countryId;

    public AgifyData(String name, Integer age, Integer count, String countryId) {
        this.name = name;
        this.age = age;
        this.count = count;
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getCount() {
        return count;
    }

    public String getCountryId() {
        return countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgifyData agifyData = (AgifyData) o;
        return name.equals(agifyData.name) && countryId.equals(agifyData.countryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countryId);
    }
}
