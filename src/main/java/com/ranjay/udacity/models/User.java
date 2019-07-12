package com.ranjay.udacity.models;

import java.util.Objects;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends StoredObject {

    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String level;

    @Override
    public String toString() {
        return "{" + " userId='" + getUserId() + "'" + ", firstName='" + getFirstName() + "'" + ", lastName='"
                + getLastName() + "'" + ", gender='" + getGender() + "'" + ", length='" + getLevel() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

}